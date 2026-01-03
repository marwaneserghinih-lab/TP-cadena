package demineur.engine;

public class Rect {
    int x;
    int y;
    int w;
    int h; 
    
    public Rect(int x_, int y_, int w_, int h_) {
        x = x_;
        y = y_;
        w = w_;
        h = h_; 
    };
    
    public boolean collidesWith(Rect other) {
        return x < other.x + other.w &&
               x + w > other.x &&
               y < other.y + other.h &&
               y + h > other.y;
    };
    
    public boolean contains(int px, int py) {
        return px >= x && px < x + w &&
               py >= y && py < y + h;
    };
}
