import java.util.Scanner;

 

public class Tablero {
    private static final int DIMENSIONS_LINE = 0;
    private static final int PUZZLE_FIRST_LINE = 1;
    private static final int PACMAN_INFO_LINE = 16;
    private static final int OUTPUT_INFO_LINE = 17;
    private static final char WALL = '*';
    private static final char HALL = ' ';
    private Pacman pacman;
    Celda[][] cellMatrix;
    int numFilas;
    int numCols;
    String[] puzzleInfo = {
            "15 17",
            "*****************",
            "*               *",
            "* ****** ****** *",
            "* *    * *    * *",
            "*               *",
            "* *    * *    * *",
            "* ****** ****** *",
            "*               *",
            "* ****** ****** *",
            "* *    * *    * *",
            "*               *",
            "* *    * *    * *",
            "* ****** ****** *",
            "*               *",
            "*****************",
            "P 1 1",
            "O 13 15"
        };

 

    public Tablero(){
        this.readPuzzleInfo();
    }

 

    private void setDimensions(){
        String dimensionsLine = this.puzzleInfo[DIMENSIONS_LINE];
        Scanner lineScan = new Scanner(dimensionsLine);
        this.numFilas = lineScan.nextInt();
        this.numCols = lineScan.nextInt();
        this.cellMatrix = new Celda[this.numFilas][this.numCols];
    }

 

    private void createCells(){
        int row;
        int column;
        int i = PUZZLE_FIRST_LINE;
        for(row = 0; row < this.numFilas; ++row) {
            String puzzleLine = this.puzzleInfo[i];
            ++i;

 

            for(column = 0; column < this.numCols; ++column) {
                char cell = puzzleLine.charAt(column);
                if (cell == WALL) {
                    this.cellMatrix[row][column] = Celda.createWallCell();
                }

 

                if (cell == HALL) {
                    this.cellMatrix[row][column] = Celda.createHallCell();
                }
            }
        }
    }

 

    public Pacman getPacman(){
        return pacman;
    }

 

    public boolean cellIsWall(int row, int column){
        return  this.cellMatrix[row][column].isWall;
    }

 

    public boolean cellHasCharacter(int row, int column){
        return  this.cellMatrix[row][column].caracter != null;
    }

 

    public boolean cellIsOutput(int row, int column){
        return this.cellMatrix[row][column].esSalida;
    }

 

    private void createPacman(){
        String pacmanInfoLine = this.puzzleInfo[PACMAN_INFO_LINE];
        Scanner lineScan  = new Scanner(pacmanInfoLine.substring(1));
        int row = lineScan.nextInt();
        int column = lineScan.nextInt();
        Posicion posicion = new Posicion(row, column);
        this.pacman = new Pacman(posicion, Juego.PUNTOS_VIDA_INICIALES);
        this.cellMatrix[posicion.fila][posicion.col].caracter = this.pacman;
    }

 

    private void setOutput(){
        String outputInfoLine = this.puzzleInfo[OUTPUT_INFO_LINE];
        Scanner lineScan  = new Scanner(outputInfoLine.substring(1));
        int row = lineScan.nextInt();
        int column = lineScan.nextInt();
        this.cellMatrix[row][column].esSalida = true;
    }

 

    private void readPuzzleInfo() {
        setDimensions();
        createCells();
        createPacman();
        setOutput();
    }

 

    public void dibujarTablero() {
        String s = "";

 

        for(int fila = 0; fila < this.numFilas; ++fila) {
            for(int col = 0; col < this.numCols; ++col) {
                s = s + this.cellMatrix[fila][col].caracterCelda();
            }

 

            s = s + "\n";
        }

 

        System.out.println(s);
    }
}
 
