package demineur.game;

public class Cell {
    /*
    Classe représentant une cellule (comme demandé dans 
    le cahiet des charges.
    */
    
    // est elle visible ?
    boolean visible;
    
    // est elle piegée ?
    boolean trapped; 
    
    public Cell(boolean visible_, boolean trapped_) {
        visible = visible_;
        trapped = trapped_; 
    };
};
