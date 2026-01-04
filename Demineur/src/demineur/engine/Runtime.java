package demineur.engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


public class Runtime extends JPanel implements Runnable {
    /*
    Le Runtime est une classe qui gère le flux d'execution du jeu, cela
    comprends plusieurs rôles assez divers (affichage, actualisation, 
    timing, ...).
    
    L'execution se fait sur deux threads, celui qui gère les evenements 
    swing, intialisé automatiquement au sein de JPanel, ainsi que le thread
    du jeu. Avoir deux threads permet une gestion non bloquante des evenements.
    */
    
   
    // Réference au Contexte.
    Context context;
    
    // Thread du jeu.
    private Thread gameThread;
    
    private volatile boolean running;
    
    // Variables utile a la gestion du timing (delta time, fps, ...)
    private long lastTime;
    private long currentTime;
    
    // FPS max du jeu.
    private static final int TARGET_FPS = 60; 
    // temps par frame.
    private static final long OPTIMAL_TIME = 1_000_000_000L / TARGET_FPS;
    
    // utile au calcul des fps.
    private int frameCount = 0;
    private long fpsTimer = 0;
    public int currentFPS = 0;
    
    // Instructions d'affichage.
    ArrayList<DrawInstruction> instructions;
    
    // ratio de pixelisation de l'ecran
    private float pixelateRatio = 0.0f;
    
    
    public Runtime(Context context_) {
        context = context_;
        // fond noir par défaut.
        setBackground(Color.BLACK);
        instructions = new ArrayList<>();
        running = false;
        setDoubleBuffered(true);
        setIgnoreRepaint(true); 
    }
    

    public void setPixelateRatio(float ratio) {
        this.pixelateRatio = ratio;
    }
    
    /*
    Abstractions appelées depuis Context pour emmettre des instructions de dessin.
    instructions étant partagé entre les threads, on veille a executer addInstrucion 
    en monothread grâce a synchronized.
    */
    
    public void addInstruction(int x, int y, String type, Color color, int[] complementary) {
        synchronized (instructions) {
            instructions.add(new DrawInstruction(x, y, type, color, complementary));
        }
    }
    
    public void addInstruction(int x, int y, BufferedImage img) {
        synchronized (instructions) {
            instructions.add(new DrawInstruction(x, y, img));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    /*
    Hook de JPanel appelé a chaque repain();
    gère le rendu.
    */
    
    super.paintComponent(g);
    // interface de rendu.
    Graphics2D g2d = (Graphics2D) g;
    
    if (pixelateRatio > 0.0f) {
        // mode pixélisé
        renderPixelated(g2d);
    } else {
        // Mode normal
        g2d.scale(context.DISPLAY_SCALE, context.DISPLAY_SCALE);
        optimizeRendering(g2d);
        handleRenderingInstructions(g2d);
    }
    
    Toolkit.getDefaultToolkit().sync();
}

    private void renderPixelated(Graphics2D g2d) {
        int width = context.DISPLAY_WIDTH;
        int height = context.DISPLAY_WIDTH;

        int pixelSize = 1 + (int)(pixelateRatio * 63);

        int smallWidth = Math.max(1, width / pixelSize);
        int smallHeight = Math.max(1, height / pixelSize);

        BufferedImage small = new BufferedImage(
            smallWidth, 
            smallHeight, 
            BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g2dSmall = small.createGraphics();
        g2dSmall.setColor(Color.BLACK);
        g2dSmall.fillRect(0, 0, smallWidth, smallHeight);

        float scale = 1.0f / pixelSize;
        g2dSmall.scale(scale, scale);

        optimizeRendering(g2dSmall);
        handleRenderingInstructions(g2dSmall);
        g2dSmall.dispose();

        g2d.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        g2d.scale(context.DISPLAY_SCALE, context.DISPLAY_SCALE);
        g2d.drawImage(small, 0, 0, width, height, null);
    }

    public void start() {
        /*
        Methode d'initialisation.
        */
        if (!running) {
            running = true;
            lastTime = System.nanoTime();
            fpsTimer = System.currentTimeMillis();
            gameThread = new Thread(this, "GameLoop");
            gameThread.setPriority(Thread.MAX_PRIORITY);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        /*
        Methode de demarrage du jeu.
        */
        while (running) {    
            // BOUCLE DU JEU 
            
            currentTime = System.nanoTime();
            handleDt();
            context.mouseClicked = false;
            context.mouseRightClicked = false;
            context.events.clear();
            repaint();
            fpsCalculation(); 
            fpsWait();
            if (context.hovered) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            context.hovered = false;
            Thread.yield();
           
        }
    }

    private void update(float dt) {
        /*
        Hook de JPannel apelé chaque frame.
        */
        synchronized (instructions) {
            instructions.clear();
        }
        // Actualisation des objets du jeu.
        ArrayList<GameObject> objects = context.objects;

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);
            if (obj.update_flag) {
                obj.update(dt);
            }
        }
    }
    
    private void optimizeRendering(Graphics2D g2d) {
        /*
        Optimisations diverses
        */
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
    }
    
    private void handleRenderingInstructions(Graphics2D g2d) {
        /*
        Gère les instructions de dessin
        */
        synchronized (instructions) {
            g2d.drawImage(context.bg, 0, 0, null);
            for (int i = 0; i < instructions.size(); i++) {
                DrawInstruction inst = instructions.get(i);
                // Dessin d'un rectangle
                if (inst.type.equals("rect")) {
                    g2d.setColor(inst.color);
                    g2d.fillRect(inst.x, inst.y, inst.complementary[0], inst.complementary[1]);
                }
                // Dessin d'une image
                if (inst.type.equals("image")) {
                    g2d.drawImage(inst.image, inst.x, inst.y, null); 
                }
                
                if (inst.type.equals("circle")) {
                    int d = inst.complementary[0];
                    int r = d / 2;

                    g2d.setColor(inst.color);
                    g2d.fillOval(inst.x - r, inst.y - r, d, d);
                }
            }
        }
    }
    
    private void fpsCalculation() {
        /*
        Calcul des fps.
        */
        frameCount++;
        long currentMillis = System.currentTimeMillis();
        if (currentMillis - fpsTimer >= 1000) {
            currentFPS = frameCount;
            frameCount = 0;
            fpsTimer = currentMillis;
        }
    }
    
    private void handleDt() {
        /*
        Gestion du delta-time.
        */
        long elapsed = currentTime - lastTime;
        float dt = elapsed / 1_000_000f;
        lastTime = currentTime;
        update(dt);
    }
    
    private void fpsWait() {
        /*
        Emet un delai entre chaque frame afin de respecter les FPS Max.
        */
        long frameTime = System.nanoTime() - currentTime;
        long sleepTime = (OPTIMAL_TIME - frameTime) / 1_000_000L;

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
    
    public void stop() {
        /*
        Methode d'arret du jeu.
        */
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void closeWindow() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); 
        }
    }
}