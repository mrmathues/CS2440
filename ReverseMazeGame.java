package solution;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * ReverseMazeGame takes the mazes from mazeGame and backtracks through them.
 * 
 * @author Max Mathues
 */
public class MazeGame2
{

    private final static int HEIGHT = 99;
    private final static int WIDTH = 99;
    private boolean[][] wall;
    private boolean[][] mark;

    public MazeGame2(String mazeFile)
    {
        loadMaze(mazeFile);
    }

    private void loadMaze(String mazeFile)
    {
        wall = new boolean[HEIGHT][WIDTH];
        mark = new boolean[HEIGHT][WIDTH];
        
        Scanner mazeScanner;
        try
        {
            mazeScanner = new Scanner(new FileReader(mazeFile));
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                    if (mazeScanner.next().equals("1"))
                    {
                        wall[i][j] = true;
                    }
                }
            }
            mazeScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + mazeFile);
        }
    }

    public String findSolution()
    {
        return backtrack(0, 0, " ");
    }

    public String backtrack(int row, int col, String pathSoFar)
    {
        String result;
        mark[row][col] = true;
        if (row == WIDTH - 1 && col == HEIGHT - 1)
        {
            return pathSoFar;
        }
        else
        {
            if (row - 1 >= 0 && mark[row - 1][col] == false 
                && wall[row - 1][col] == false)
            {
                result = backtrack(row - 1, col, pathSoFar + "up ");
                if (result != null)
                {
                    return result;
                }
            }
            if (col + 1 <= WIDTH - 1 && mark[row][col + 1] == false 
                && wall[row][col + 1] == false)
            {
                result = backtrack(row, col + 1, pathSoFar + "right ");
                if (result != null)
                {
                    return result;
                }
            }
            if (row + 1 <= HEIGHT - 1 && mark[row + 1][col] == false 
                && wall[row + 1][col] == false)
            {
                result = backtrack(row + 1, col, pathSoFar + "down ");
                if (result != null)
                {
                    return result;
                }
            }
            if (col - 1 >= 0 && mark[row][col - 1] == false 
                && wall[row][col - 1] == false)
            {
                result = backtrack(row, col - 1, pathSoFar + "left ");
                if (result != null)
                {
                    return result;
                }
                
            }
            else
            {
                return null;
            }
        }
        return null;
    }

    public void printMap()
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if (wall[i][j])
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
