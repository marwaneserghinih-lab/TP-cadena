package demineur.engine;

import demineur.engine.Rect;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Context extends javax.swing.JFrame {
    /*
    Le Contexte de jeu est l'objet central de celui ci, héritant de JFrame, il 
    permet de setup la fenêtre du jeu ainsi que de centraliser la communication 
    des objets en son sein. En effet, la majorité des elements du jeu sont 
    couplés au contexte et peuvent y acceder librement. Le contexte est donc
    conçu pour être l'objet utilitaire par excellence, il centralise l'api 
    du moteur et permet d'interagir avec chaque élément de celui-ci via des 
    commandes simples.
    */
    
    // liste d'objets du jeu
    public java.util.ArrayList<GameObject> objects = new java.util.ArrayList<>(); 
    
    // HashMap d'état du clavier (touche : état)
    public java.util.HashMap<Integer, Boolean> keys = new java.util.HashMap<>(); 
    
    // Liste des evenements non traités
    public java.util.ArrayList<Event> events = new java.util.ArrayList<>();
    
    // HashMap d'enregistrement des images (nom : image) 
    private java.util.HashMap<String, BufferedImage> images = new java.util.HashMap<>();
   
    
    // Position de la souris 
    public int mouseX = 0;
    public int mouseY = 0;
    public boolean mousePressed = false;
    public volatile boolean mouseClicked = false;
    public boolean mouseRightPressed = false;
    public volatile boolean mouseRightClicked = false;
    public boolean hovered = false;
    
    // Résolution (calquée sur la NES)
    public static final int DISPLAY_WIDTH = 256;
    public static final int DISLAY_HEIGHT = 240;
    
    // Redimentionnement de la fenêtre (sinon elle serait trop petite)
    public static final int DISPLAY_SCALE = 2;
   
    // Autres éléments du moteur
    private Runtime runtime;
    public SceneManager sceneManager = null;
    
    Image bg = Toolkit.getDefaultToolkit().getImage("src/assets/bg.gif");
    
    private Dimension windowedSize;
   
    
    public Context(String title) {
        
        setTitle(title);
        windowedSize = new Dimension(
            DISPLAY_WIDTH * DISPLAY_SCALE, 
            DISLAY_HEIGHT * DISPLAY_SCALE
        );
        
        setSize(windowedSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
       
        setupRuntime();
        
        objects = new java.util.ArrayList<GameObject>();
    }
    
    /* 
    API DU MOTEUR 
    */
    
    public void setPixelated(float ratio) {
        /*
        Permet pixeliser l'ecran.
        ratio (float): 
            valeur entre 0 et 1 qui controle le ratio de pixelisation.
            0 : pas pixelisé
            -> 1 : très pixélisé.   
        */
        runtime.setPixelateRatio(ratio);
    }
    
    public void drawRect(Rect rect, Color color) {
        /*
        Dessine un rectangle.
        rect (Rect):
            rectangle a dessiner.
        color (Color):
            sa couleur.
        */
        runtime.addInstruction(rect.x, rect.y, "rect", color, new int[]{rect.w, rect.h});
    }
    
    public void drawCircle(int x, int y, int radius, Color color) {
        runtime.addInstruction(x, y, "circle", color, new int[]{radius});
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, Color color, int width) {
        /*
        Dessine une ligne.
        (x1, y1):
            position du premier point.
        (x2, y2):
            position du second point.
        color (Color):
            couleur de la ligne.
        width (int):
            epaisseur de la ligne.
        */
        runtime.addInstruction(x1, y1, "line", color, new int[]{x2, y2});
    }
    
    public void addObject(GameObject object) {
        /*
        Ajoute un objet au contexte.
        object (GameObject):
            objet a ajouter.
        */
        objects.add(object);
    }
    
    public void loadImage(String name, String path) {
        /*
        Charge une image dans le contexte.
        name (String):
            son nom d'enregistrement (a réutiliser pour invoquer l'image).
        path (String):
            son chemin relatif a la source du projet.
        */
        try {
            BufferedImage img = ImageIO.read(new File(path));
            images.put(name, img);
        } catch (IOException e) {
            System.err.println("Image Loading Error");
            e.printStackTrace();
        }
    }
    
    public void loadImages(String name, String path, int sliceX, int sliceY) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            for (int i = 0; i < (int)(img.getWidth() / sliceX); i++) {
                for (int j = 0; j < (int)(img.getHeight() / sliceY); j++) {
                    images.put(
                        name + Integer.toString(i) + "." + Integer.toString(j), 
                        img.getSubimage(i * sliceX, j * sliceY, sliceX, sliceY)
                    );
                }
            }
            images.put(name, img);
        } catch (IOException e) {
            System.err.println("Image Loading Error");
            e.printStackTrace();
        }
    }
    
    public void drawImage(String name, int x, int y) {
        /*
        Dessine une image.
        name (String):
            nom de l'image.
        (x, y):
            position de l'ecran ou la dessiner.
        */
        runtime.addInstruction(x, y, images.get(name));
    };
    
    
  
    
    /*
    METHODES INTERNES
    */

    private void setupRuntime() {
        /*
        gère toutes les opérations relatives au runtime
        (aurait pu être fait dans le runtime, c'est 
        objectivement un choix de design inutile mais j'ai 
        concrêtement la flemme de refactoriser).
        */
        runtime = new Runtime(this);
        add(runtime);
        setVisible(true);
        runtime.start();
        handleKeyboard();
        handleMouse();
        runtime.setFocusable(true);
        runtime.requestFocusInWindow();
    }
    
    private void handleKeyboard() {
        /* 
        Gestion du clavier. 
        */
        runtime.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                keys.put(e.getKeyCode(), true);
                events.add(new Event("keydown", new int[]{e.getKeyCode()}));          
            }
            
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                keys.put(e.getKeyCode(), false);
            }
            
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {}
        });
    }
    
    private void handleMouse() {
    /*
    Gestion de la souris.
    */
        runtime.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                updateMouseCoordinates(e);
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                updateMouseCoordinates(e);
            }
        });

        runtime.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                updateMouseCoordinates(e);

                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    // Clic gauche
                    mousePressed = true;
                    mouseClicked = true;
                } else if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    // Clic droit
                    mouseRightPressed = true;
                    mouseRightClicked = true;
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                updateMouseCoordinates(e);

                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    mousePressed = false;
                } else if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    mouseRightPressed = false;
                }
            }
        });
    }
    
    private void updateMouseCoordinates(java.awt.event.MouseEvent e) {
        /*
        Actualisation de la position de la souris.
        */
        mouseX = e.getX() / DISPLAY_SCALE;
        mouseY = e.getY() / DISPLAY_SCALE;
    }
    
    public void quit() {
        runtime.closeWindow();
    }
}