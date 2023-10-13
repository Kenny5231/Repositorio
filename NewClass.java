package programacion.pkg1__;

import java.util.Scanner;
import java.util.Random;

public class NewClass{
    public static void main(String[] args) {
        char[][] board = new char[8][8];
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Inicializa el tablero
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = ' ';
            }
        }

        // Coloca los barcos en posiciones aleatorias
        int numBarcos = 5; // Número total de barcos
        for (int i = 0; i < numBarcos; i++) {
            int rowIdx = random.nextInt(8);
            int colIdx = random.nextInt(8);

            // Verifica si ya hay un barco en esa posición
            while (board[rowIdx][colIdx] == 'B') {
                rowIdx = random.nextInt(8);
                colIdx = random.nextInt(8);
            }

            board[rowIdx][colIdx] = 'B'; // Marcar como barco
        }

        // Código de juego simplificado en modo arcade
        boolean gameOver = false;
        while (!gameOver) {
            // Mostrar el tablero (sin mostrar la ubicación de los barcos)
            System.out.println("Tablero de juego:");
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] == 'X' || board[row][col] == 'O') {
                        System.out.print(board[row][col] + " ");
                    } else {
                        System.out.print("  "); // Espacios en blanco para ocultar barcos
                    }
                }
                System.out.println();
            }

            // Obtener la entrada del jugador
            System.out.print("Ingrese la fila y columna (por ejemplo, 1 2): ");
            int rowIdx = scanner.nextInt();
            int colIdx = scanner.nextInt();

            // Realizar un ataque en la posición ingresada
            if (board[rowIdx][colIdx] == 'B') {
                System.out.println("¡Golpeaste un barco!");
                board[rowIdx][colIdx] = 'X'; // Marcar como golpeado
            } else {
                System.out.println("¡Agua!");
                board[rowIdx][colIdx] = 'O'; // Marcar como agua
            }

            // Verificar si se ha alcanzado el estado de finalización del juego
            int barcosRestantes = numBarcos;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] == 'X') {
                        barcosRestantes--;
                    }
                }
            }

            if (barcosRestantes == 0) {
                gameOver = true;
                System.out.println("¡Has hundido todos los barcos!");
            }
        }

        // Fin del juego
        System.out.println("¡Fin del juego!");
    }
}
