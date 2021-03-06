import java.util.Scanner;
public class Juego {
    public static final int PUNTOS_VIDA_INICIALES = 10;
    Tablero board = new Tablero();
    Pacman pacman = board.getPacman();
    private static final String UP = "w";
    private static final String DOWN = "s";
    private static final String LEFT = "a";
    private static final String RIGHT = "d";
    private static final String QUIT = "q";

 

    public Juego() {
    }

 

    public void jugar() {
        boolean wonGame = false;
        board.dibujarTablero();
        Scanner systemInput = new Scanner(System.in);
        String userInput = systemInput.nextLine();
        while (!userInput.equals(QUIT) && !wonGame) {
            int pacmanRow = pacman.posicion.fila;
            int pacmanColumn = pacman.posicion.col;
            int newRow = pacmanRow;
            int newColumn = pacmanColumn;
            switch (userInput) {
                case UP:
                newRow = pacmanRow - 1;
                break;
                case DOWN:
                newRow = pacmanRow + 1;
                break;
                case LEFT:
                newColumn = pacmanColumn - 1;
                break;
                case RIGHT:
                newColumn = pacmanColumn + 1;
                break;
            }
            if (cellIsValid(newRow, newColumn)) {
                Celda previousCell = board.cellMatrix[pacmanRow][pacmanColumn];
                Celda newCell = board.cellMatrix[newRow][newColumn];
                newCell.caracter = pacman;
                previousCell.caracter = null;
                pacman.posicion = new Posicion(newRow, newColumn);
                if(board.cellIsOutput(newRow, newColumn)){

 

                    wonGame = true;
                    System.out.println("Se define wonGame");
                    board.dibujarTablero();
                    break;
                }

 

            }
            board.dibujarTablero();
            userInput = systemInput.nextLine();
        }
        if (wonGame) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
        System.out.println("Fin del juego");
    }

 

    
    private boolean cellIsValid(int newRow, int newColumn) {
        return (
            !board.cellIsWall(newRow, newColumn) &&
            !board.cellHasCharacter(newRow, newColumn)
        );
    }
}


