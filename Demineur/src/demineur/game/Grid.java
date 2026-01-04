package demineur.game;
import demineur.engine.*;

public class Grid extends GameObject {
    private int sizeX;
    private int sizeY; 
    private int x;
    private int y; 
    private Cell[][] cells;
    private boolean[][] flags;
    
    public Grid(Context context, int bombs, int sizeX_, int sizeY_, int initX, int initY) {
        super(context);
        sizeX = sizeX_;
        sizeY = sizeY_;
        x = initX;
        y = initY;
        initCells(bombs);
    }
    
    private void initCells(int bombs) {
        cells = new Cell[sizeY][sizeX];
        flags = new boolean[sizeY][sizeX];
        
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[j][i] = new Cell(false, false);
                flags[j][i] = false;
            }
        }
        
        placeBombs(bombs);
        center();
    }
    
    private void placeBombs(int bombs) {
        int placed = 0;
        while (placed < bombs) {
            int posX = (int)(Math.random() * sizeX);
            int posY = (int)(Math.random() * sizeY);
            
            if (!cells[posY][posX].trapped) {
                cells[posY][posX].trapped = true;
                placed++;
            }
        }
    }
    
    public void update(float dt) {
        drawGrid();
        drawSelector();
        handleMouse();
    }
    
    private void drawGrid() {
        for (int i = 0; i < sizeX; i++) {
            context.drawImage("elements12.0", x + i * 16, y + sizeY * 16);
            for (int j = 0; j < sizeY; j++) {
                drawCell(i, j);
            }
        }
    }
    
    private void drawCell(int i, int j) {
        int trappedAround = countBombsAround(i, j);
        
        if (cells[j][i].visible) {
            drawVisibleCell(i, j, trappedAround);
        } else {
            drawHiddenCell(i, j);
        }
    }
    
    private void drawVisibleCell(int i, int j, int trappedAround) {
        context.drawImage("elements1.0", x + i * 16, y + j * 16);
        
        if (cells[j][i].trapped) {
            context.drawImage("elements11.0", x + i * 16, y + j * 16);
        } else if (trappedAround != 0) {
            context.drawImage("elements" + (trappedAround + 1) + ".0", x + i * 16, y + j * 16);
        }
    }
    
    private void drawHiddenCell(int i, int j) {
        context.drawImage("elements0.0", x + i * 16, y + j * 16);
        
        if (flags[j][i]) {
            context.drawImage("elements10.0", x + i * 16, y + j * 16);
        }
    }
    
    private int countBombsAround(int i, int j) {
        int count = 0;
        
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k != 0 || l != 0) {
                    if (isBombAt(i + k, j + l)) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private boolean isBombAt(int i, int j) {
        try {
            return cells[j][i].trapped;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    
    private void drawSelector() {
        int[] selectPos = gridMousePos();
        
        if (isValidPosition(selectPos[0], selectPos[1])) {
            context.drawImage("select", x + selectPos[0] * 16 - 7, y + selectPos[1] * 16 - 10);
        }
    }
    
    private boolean isValidPosition(int i, int j) {
        return i >= 0 && i < sizeX && j >= 0 && j < sizeY;
    }
    
    private void handleMouse() {
        if (context.mouseClicked) {
            int[] pos = gridMousePos();
            cells[pos[1]][pos[0]].visible = true;
        };
        if (context.mouseRightClicked) {
            int[] pos = gridMousePos();
            flags[pos[1]][pos[0]] = !flags[pos[1]][pos[0]];
        }
    }
    
    public void center() {
        x = (context.DISPLAY_WIDTH - sizeX * 16) / 2 - 5;
        y = (context.DISLAY_HEIGHT - sizeY * 16) / 2 - 10;
    }
    
    public int[] gridMousePos() {
        return new int[] {
            (int)(Math.floorDiv(context.mouseX - x, 16)),
            (int)(Math.floorDiv(context.mouseY - y, 16))
        };
    }
}