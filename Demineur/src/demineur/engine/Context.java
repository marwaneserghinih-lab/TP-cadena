package demineur.engine;

import demineur.engine.Rect;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Context extends javax.swing.JFrame {
    
    private Runtime runtime;
    public java.util.ArrayList<GameObject> objects;
    public java.util.HashMap<Integer, Boolean> keys = new java.util.HashMap<>();
    private java.util.HashMap<String, BufferedImage> images = new java.util.HashMap<>();
    public int mouseX = 0;
    public int mouseY = 0;
    public boolean mousePressed = false;
    public volatile boolean mouseClicked = false;
    
    private static final int NES_WIDTH = 256;
    private static final int NES_HEIGHT = 240;
    private static final int SCALE = 2;
    private GraphicsDevice graphicsDevice;
    private Dimension windowedSize;
    public SceneManager sceneManager;
   
    
    public Context(String title, SceneManager sceneManager_) {
        setTitle(title);
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        windowedSize = new Dimension(NES_WIDTH * SCALE, NES_HEIGHT * SCALE);
        setSize(windowedSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        objects = new java.util.ArrayList<GameObject>();
        setupRuntime();
        sceneManager = sceneManager_; 
    }
    
    public void setPixelated(float ratio) {
        runtime.setPixelateRatio(ratio);
    }
    
    public void drawRect(Rect rect, Color color) {
        runtime.addInstruction(rect.x, rect.y, "rect", color, new int[]{rect.w, rect.h});
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, Color color, int width) {
        runtime.addInstruction(x1, y1, "line", color, new int[]{x2, y2});
    }
    
    public void addObject(GameObject object) {
        objects.add(object);
    }
    
    public void loadImage(String name, String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            images.put(name, img);
        } catch (IOException e) {
            System.err.println("Image Loading Error");
            e.printStackTrace();
        }
    }
    
    public void drawImage(String name, int x, int y) {
        runtime.addInstruction(x, y, images.get(name));
    }

    
    
    public int getDisplayWidth() {
        return NES_WIDTH;
    }
    
    public int getDisplayHeight() {
        return NES_HEIGHT;
    }
    
    public int getScale() {
        return SCALE;
    }
    
    private void setupRuntime() {
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
        runtime.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                keys.put(e.getKeyCode(), true);
                        
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
                mousePressed = true;
                mouseClicked = true;
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                updateMouseCoordinates(e);
                mousePressed = false;
            }
        });
    }
    
    private void updateMouseCoordinates(java.awt.event.MouseEvent e) {
        mouseX = e.getX() / SCALE;
        mouseY = e.getY() / SCALE;
        
    }
}