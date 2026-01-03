package demineur.engine;

import java.awt.image.BufferedImage;

public class Instruction {
    int x;
    int y;
    String type;
    java.awt.Color color;
    int[] complementary;   
    BufferedImage image;
    
    public Instruction(int x_, int y_, String type_, java.awt.Color color_, int[] complementary_) {
        x = x_;
        y = y_;
        type = type_;
        color = color_;
        complementary = complementary_; 
        image = null;
    };
    public Instruction(int x_, int y_, BufferedImage image_) {
        x = x_;
        y = y_;
        type = "image";
        color = null;
        complementary = null; 
        image = image_;
    };
};
