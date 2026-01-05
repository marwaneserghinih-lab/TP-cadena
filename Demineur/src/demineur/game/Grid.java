package demineur.game;
import demineur.engine.*;

public class Grid extends GameObject {
    /*
    classe représentant la grille (comme demandé dans le 
    cahiet des charges).
    */
    
    // taille de la grille.
    private int sizeX;
    private int sizeY; 
    
    // position de la grille.
    private int x;
    private int y; 
    
    // cellules.
    private Cell[][] cells;
    
    // configuration des drapeaux.
    private boolean[][] flags;
    
    // état du jeu :
    // 0 : neutre, 1 : perdu, 2 : gagné.
    public int gameState = 0;
    
    // la fin est elle survenue ?
    public boolean endConsumed = false;
    
    // mode "arcade" ou mode "classique" ?
    String mode;
    
    // nombre de bombes.
    int bombs;
    
    public Grid(Context context, int bombs_, int sizeX_, int sizeY_, int initX, int initY, String mode_) {
        super(context);
        sizeX = sizeX_;
        sizeY = sizeY_;
        x = initX;
        y = initY;
        bombs = bombs_;
        mode = mode_;
        initCells(bombs);
    };
    
    private void initCells(int bombs) {
        /*
        Initialise le tableau de cellules, ainsi que la configuration
        des drapeaux.
        */
        cells = new Cell[sizeY][sizeX];
        flags = new boolean[sizeY][sizeX];
        
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[j][i] = new Cell(false, false);
                flags[j][i] = false;
            };
        };
        
        placeBombs(bombs);
        center();
    };
    
    private void placeBombs(int bombs) {
        /*
        Place les bombes aléatoirements.
        */
        int placed = 0;
        while (placed < bombs) {
            int posX = (int)(Math.random() * sizeX);
            int posY = (int)(Math.random() * sizeY);
            
            if (!cells[posY][posX].trapped) {
                cells[posY][posX].trapped = true;
                placed++;
            };
        };
    };
    
    public void update(float dt) {
        drawGrid();
        drawSelector();
        handleMouse();
        checkGameState();
        context.currentMode = mode;
        endHandling();
    };
    
    private void drawGrid() {
        /*
        Dessine la grille.
        */
        for (int i = 0; i < sizeX; i++) {
            context.drawImage("elements12.0", x + i * 16, y + sizeY * 16);
            for (int j = 0; j < sizeY; j++) {
                drawCell(i, j);
            };
        };
    };
    
    private void drawCell(int i, int j) {
        /*
        Dessine une cellule.
        */
        int trappedAround = countBombsAround(i, j);
        
        if (cells[j][i].visible) {
            drawVisibleCell(i, j, trappedAround);
        } else {
            drawHiddenCell(i, j);
        };
    };
    
     private int countBombsAround(int i, int j) {
        /*
        Compte les bombes autours d'une position.
        */
        int count = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k != 0 || l != 0) {
                    if (isBombAt(i + k, j + l)) {
                        count++;
                    };
                };
            };
        };
        return count;
    };
    
    private boolean isBombAt(int i, int j) {
        /*
        Vérifie si une bombe est placé a une certaine position.
        */
        try {
            return cells[j][i].trapped;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    };
    
    private void drawVisibleCell(int i, int j, int trappedAround) {
        /*
        Dessine une cellule visible.
        */
        context.drawImage("elements1.0", x + i * 16, y + j * 16);
        
        if (cells[j][i].trapped) {
            context.drawImage("elements11.0", x + i * 16, y + j * 16);
        } else if (trappedAround != 0) {
            context.drawImage("elements" + (trappedAround + 1) + ".0", x + i * 16, y + j * 16);
        };
    };
    
    private void drawHiddenCell(int i, int j) {
        /*
        Dessine une cellule non visible.
        */
        context.drawImage("elements0.0", x + i * 16, y + j * 16);
        
        if (flags[j][i]) {
            context.drawImage("elements10.0", x + i * 16, y + j * 16);
        };
    };
    
     
    private void drawSelector() {
        /*
        Dessine le sprite de selection (l'espèce de carré rouge).
        */
        int[] selectPos = gridMousePos();
        
        if (isValidPosition(selectPos[0], selectPos[1]) && !cells[selectPos[1]][selectPos[0]].visible) {
            context.hovered = true;
            context.drawImage("select", x + selectPos[0] * 16 - 7, y + selectPos[1] * 16 - 10);
        };
    };
    
    private void handleMouse() {
        /*
        Gère la souris.
        */
        int[] pos = gridMousePos();
        if (!isValidPosition(pos[0], pos[1])) {
            return;
        };
        if (context.mouseClicked) {
            reveal(pos[0], pos[1]);
        };
        if (context.mouseRightClicked) {
            if (!cells[pos[1]][pos[0]].visible)
                flags[pos[1]][pos[0]] = !flags[pos[1]][pos[0]];
        };
    };
    
    public int[] gridMousePos() {
        /*
        récupère la position de la souris dans le référentiel de 
        la grille.
        */
        return new int[] {
            (int)(Math.floorDiv(context.mouseX - x, 16)),
            (int)(Math.floorDiv(context.mouseY - y, 16))
        };
    };
    
    
    private boolean isValidPosition(int i, int j) {
        /*
        Vérifie si une position est dans la grille.
        */
        return i >= 0 && i < sizeX && j >= 0 && j < sizeY;
    };
    
    private void endHandling() {
        /*
        Gère les actions à faire en cas de fin du jeu.
        */
        if (!endConsumed) {
            if (gameState == 1) {
                lose();
                endConsumed = true;
            } else if (gameState == 2) {
                win();
         
                if (mode.equals("classique")) {
                    endConsumed = true;
                }
               
            }
        }

        if (gameState == 1) {
            context.screenShake(5);
        };
    };
    
    private void checkGameState() {
        /*
        vérifie si la partie est gagnée.
        */
        boolean win = true;
        boolean lose = false;
        
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                // Vérifier si bombe révélée = perdu
                if (cells[j][i].visible && cells[j][i].trapped) {
                    lose = true;
                    win = false; 
                };
                
                if (mode.equals("classique")) {
                    // Mode classique : juste révéler toutes les cases libres
                    if (!cells[j][i].trapped && !cells[j][i].visible) {
                        win = false;
                    }
                } else {
                    // Mode arcade : révéler cases libres et flaguer toutes les bombes
                    if (!cells[j][i].trapped && !cells[j][i].visible) {
                        win = false;
                    };
                    if (cells[j][i].trapped && !flags[j][i]) {
                        win = false;
                    };
                };
            };
        };
        
        // actualisation de l'état du jeu.
        if (lose)
            gameState = 1;
        else if (win)
            gameState = 2;
    };
    
    public void center() {
        /*
        Centre la grille par rapport a l'écran.
        */
        x = (context.DISPLAY_WIDTH - sizeX * 16) / 2 - 5;
        y = (context.DISLAY_HEIGHT - sizeY * 16) / 2 - 10;
    };
    
  
    
    public void reveal(int i, int j) {
        /*
        Fonction récursive de type flood-fill qui révèles les cases
        vides jusqu'à croiser une bombe (comme dans un démineur 
        classique).
        */
        
        if (i < 0 || i >= sizeX || j < 0 || j >= sizeY) {
            return;
        };
        
        if (cells[j][i].visible || flags[j][i]) {
            return;
        };
        
        cells[j][i].visible = true;
        
        if (cells[j][i].trapped) {
            return;
        };
        
        int bombsAround = countBombsAround(i, j);
        
        if (bombsAround == 0) {
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (k != 0 || l != 0) {
                        reveal(i + k, j + l);                 
                    };
                };
            };
        };
    };
    
    private void reset() {
        /*
        Réinitialise l'état du jeu.
        */
        gameState = 0;
        endConsumed = false;
        context.arcadeTime = 15;
        
        if (mode.equals("arcade")) {
            bombs = 3;
        };
        
        initCells(bombs);
    };
    
    private void lose() {
        /*
        Apelée lors d'une défaite.
        */
        if (mode.equals("classique"))
            context.sceneManager.transition("perdu", (long)2.5, "circle");
        else
            context.sceneManager.transition("perdu", (long)2.5, "circle");
    };
    
    private void win() {
        /*
        Apelée lors d'une victoire.
        */
        if (mode.equals("classique")) {
            context.sceneManager.transition("gagne", (long)4.0, "circle"); 
        } else {
            context.arcadeScore++;
            reset();
        };
    };
};