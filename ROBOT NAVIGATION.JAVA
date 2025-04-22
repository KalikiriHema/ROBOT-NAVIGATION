import java.util.Scanner;

public class RobotNavigation {

    // Directions: Right (0, 1), Down (1, 0), Left (0, -1), Up (-1, 0)
    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};

    // Function to solve the maze
    public static boolean solveMaze(int[][] maze, int x, int y, int[][] sol) {
        // If (x, y) is the destination
        if (x == maze.length - 1 && y == maze[0].length - 1 && maze[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }

        // Check if current position is valid
        if (isSafe(maze, x, y)) {
            sol[x][y] = 1; // Mark the current cell as part of the solution

            // Move right
            if (solveMaze(maze, x + 1, y, sol)) return true;

            // Move down
            if (solveMaze(maze, x, y + 1, sol)) return true;

            // Move left
            if (solveMaze(maze, x - 1, y, sol)) return true;

            // Move up
            if (solveMaze(maze, x, y - 1, sol)) return true;

            // Backtrack: unmark the cell if no movement was possible
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    // Function to check if a cell (x, y) is valid for the path
    public static boolean isSafe(int[][] maze, int x, int y) {
        // Check if x, y is within bounds and is an open cell (0)
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0);
    }

    // Function to print the solution path
    public static void printSolution(int[][] sol) {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[i].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read maze size
        System.out.print("Enter the size of the maze (N x N): ");
        int N = scanner.nextInt();

        // Initialize the maze
        int[][] maze = new int[N][N];
        System.out.println("Enter the maze row by row (0 for open path, 1 for wall):");

        // Input the maze
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maze[i][j] = scanner.nextInt();
            }
        }

        // Solution matrix to store the path
        int[][] sol = new int[N][N];

        // Start solving the maze from the top-left corner (0, 0)
        if (solveMaze(maze, 0, 0, sol)) {
            System.out.println("Solution found:");
            printSolution(sol); // Print the solution
        } else {
            System.out.println("No solution found");
        }

        scanner.close();
    }
}
