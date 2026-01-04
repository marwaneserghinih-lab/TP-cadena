package demineur.engine;

import java.awt.image.BufferedImage;

public class DrawInstruction {
    /*
    La classe DrawInstruction agit comme une instruction de dessin, a 
    destination du Runtime, il peut s'agir de dessiner un rectangle, une
    image, ... toutes les informations spécifiques a cette opération 
    seront contenue dans l'instruction. Conceptuelement, cette classe 
    permet de séparer les responsabilitées entre le Contexte et le Runtime
    en agissant comme une interface de rendu entre les deux.
    */
    
    // position du dessin.
    int x; 
    int y;
    // type de dessin (rectangle ? image ? ...).
    String type;
    // couleur du dessin.
    java.awt.Color color;
    // possible image.
    BufferedImage image;
    // informations complémentaires.
    int[] complementary;   
    
    public DrawInstruction(int x_, int y_, String type_, java.awt.Color color_, int[] complementary_) {
        x = x_;
        y = y_;
        type = type_;
        color = color_;
        complementary = complementary_; 
        image = null;
    };
    
    public DrawInstruction(int x_, int y_, BufferedImage image_) {
        x = x_;
        y = y_;
        type = "image";
        color = null;
        complementary = null; 
        image = image_;
    };
    
    public DrawInstruction(int x_, int y_, BufferedImage image_, int[] complementary_) {
        x = x_;
        y = y_;
        type = "image";
        color = null;
        complementary = complementary_; 
        image = image_;
    };
};
