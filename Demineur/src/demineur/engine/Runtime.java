package demineur.engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Runtime extends JPanel implements Runnable {
    Context context;
    ArrayList<Instruction> instructions;
    
    private Thread gameThread;
    private volatile boolean running;
    
    private long lastTime;
    private long currentTime;
    
    private static final int TARGET_FPS = 60; 
    private static final long OPTIMAL_TIME = 1_000_000_000L / TARGET_FPS;
    
    private int frameCount = 0;
    private long fpsTimer = 0;
    
    public int currentFPS = 0;
    private float pixelateRatio = 0.0f;
    
    public Runtime(Context context_) {
        context = context_;
        setBackground(Color.BLACK);
        instructions = new ArrayList<>();
        running = false;
        setDoubleBuffered(true);
        setIgnoreRepaint(true); 
    }
    

    public void setPixelateRatio(float ratio) {
        this.pixelateRatio = ratio;
    }

    public void addInstruction(int x, int y, String type, Color color, int[] complementary) {
        synchronized (instructions) {
            instructions.add(new Instruction(x, y, type, color, complementary));
        }
    }
    
    public void addInstruction(int x, int y, BufferedImage img) {
        synchronized (instructions) {
            instructions.add(new Instruction(x, y, img));
        }
    }
    
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    
    if (pixelateRatio > 0.0f) {
        // ✅ Mode pixelate
        renderPixelated(g2d);
    } else {
        // Mode normal
        g2d.scale(context.getScale(), context.getScale());
        optimizeRendering(g2d);
        handleRenderingInstructions(g2d);
    }
    
    Toolkit.getDefaultToolkit().sync();
}

private void renderPixelated(Graphics2D g2d) {
    int width = context.getDisplayWidth();
    int height = context.getDisplayHeight();
    
    // Taille de pixel: 1 → 64 selon le ratio
    int pixelSize = 1 + (int)(pixelateRatio * 63);
    
    // Taille réduite
    int smallWidth = Math.max(1, width / pixelSize);
    int smallHeight = Math.max(1, height / pixelSize);
    
    // Render dans une petite image
    BufferedImage small = new BufferedImage(
        smallWidth, 
        smallHeight, 
        BufferedImage.TYPE_INT_RGB
    );
    
    Graphics2D g2dSmall = small.createGraphics();
    g2dSmall.setColor(Color.BLACK);
    g2dSmall.fillRect(0, 0, smallWidth, smallHeight);
    
    // Scale pour fitter dans la petite taille
    float scale = 1.0f / pixelSize;
    g2dSmall.scale(scale, scale);
    
    optimizeRendering(g2dSmall);
    handleRenderingInstructions(g2dSmall);
    g2dSmall.dispose();
    
    // Upscale avec pixels nets
    g2d.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
    );
    
    g2d.scale(context.getScale(), context.getScale());
    g2d.drawImage(small, 0, 0, width, height, null);
}
    
    public void start() {
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
        while (running) {       
            currentTime = System.nanoTime(); 
            
            handleDt();
            context.mouseClicked = false;
         
            
            
            repaint();
            fpsCalculation(); 
            fpsWait();
            Thread.yield();
        }
    }
    
    private void update(float dt) {
        synchronized (instructions) {
            instructions.clear();
        }
        
        ArrayList<GameObject> objects = context.objects;
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);
            if (obj.update_flag) {
                obj.update(dt);
            }
        }
    }
    
    private void optimizeRendering(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
    }
    
    private void handleRenderingInstructions(Graphics2D g2d) {
        synchronized (instructions) {
            for (int i = 0; i < instructions.size(); i++) {
                Instruction inst = instructions.get(i);
                if (inst.type.equals("rect")) {
                    g2d.setColor(inst.color);
                    g2d.fillRect(inst.x, inst.y, inst.complementary[0], inst.complementary[1]);
                }
                
                if (inst.type.equals("image")) {
                   
                    g2d.drawImage(inst.image, inst.x, inst.y, null); 
                }
            }
        }
    }
    
    private void fpsCalculation() {
        frameCount++;
        long currentMillis = System.currentTimeMillis();
        if (currentMillis - fpsTimer >= 1000) {
            currentFPS = frameCount;
            frameCount = 0;
            fpsTimer = currentMillis;
        }
    }
    
    private void handleDt() {
        long elapsed = currentTime - lastTime;
        float dt = elapsed / 1_000_000f;
        lastTime = currentTime;
        update(dt);
    }
    
    private void fpsWait() {
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
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}