package demineur.engine;

public class Rect {
    /*
    La classe Rect permet de définir des rectangles par
    leur position et leur taille.
    On peut aussi détecter les collisions entre rectangles, ou
    les collisions Rectangle - Point (utile pour l'UI).
    */
    
    public int x; // position en x
    public int y; // position en y
    public int w; // largeur
    public int h; // hauteur
    
    public Rect(int x_, int y_, int w_, int h_) {
        x = x_;
        y = y_;
        w = w_;
        h = h_; 
    };
    
    public boolean collidesWith(Rect other) {
        /*
        Detecte la collision avec un autre rectangle.
        other (Rect):
            l'autre rectangle.
        Return (boolean):
            la resultat de la détéction.
        */
        return x < other.x + other.w &&
               x + w > other.x &&
               y < other.y + other.h &&
               y + h > other.y;
    };
    
    public boolean contains(int px, int py) {
        /*
        Detecte la collision avec un point.
        (px, py):
            la position du point.
        Return (boolean):
            la resultat de la détéction.
        */
        return px >= x && px < x + w &&
               py >= y && py < y + h;
    };
}
