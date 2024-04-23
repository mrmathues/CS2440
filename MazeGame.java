package maze;
import java.util.Scanner;
import java.io.*;


public class MazeGame
{
    public static final int HEIGHT = 19;
    public static final int WIDTH = 39;
    private static final int COL = 1;
    private static final int ROW = 0;
    private Scanner playerInput;
    private boolean[][] blocked;
    private boolean[][] visited;
    private int[] player;
    private int[] goal;
    private int[] start;

    public MazeGame(String mazeFile, Scanner playerInput) throws FileNotFoundException{
        this.playerInput = playerInput;
        loadMaze(mazeFile);
    }
    public MazeGame(String mazeFile) throws FileNotFoundException{
        this(mazeFile, new Scanner(System.in));
    
    }

    public void playGame(){

        do {
            prompt();
        } while (!makeMove(playerInput.next().toLowerCase()));

        if (playerAtGoal()){
            System.out.println("You Won!");
        } else {
            System.out.println("Goodbye!");
        }
    }

    public void printMaze(){
        System.out.println("*" + "---------------------------------------" + "*");
        for (int i = 0; i < HEIGHT; i++){
            System.out.print("|");
            for (int j = 0; j < WIDTH; j++){
                if (player[ROW] == i && player[COL] == j){
                    System.out.print("@");
                } else if (start[ROW] == i && start[COL] == j){
                    System.out.print("S");
                } else if (goal[ROW] == i && goal[COL] == j){
                    System.out.print("G");
                } else if (visited[i][j]){
                    System.out.print(".");
                } else if (blocked[i][j]){
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        System.out.println("*" + "---------------------------------------" + "*");
    }

    public int getPlayerRow(){
        return this.player[ROW];
    }
    public int getPlayerCol(){
        return this.player[COL];  
    }

    public int getGoalRow(){
        return this.goal[ROW];
    }
    public int getGoalCol(){
        return this.goal[COL];
    }

    public int getStartRow(){
        return this.start[ROW];
    }
    public int getStartCol(){
        return this.start[COL];
    }

    public boolean[][] getBlocked(){
        return copyTwoDimBoolArray(blocked);
    }
    public boolean[][] getVisited(){
        return copyTwoDimBoolArray(visited);
    }

    public Scanner getPlayerInput(){
        return playerInput;
    }

    public void setPlayerRow(int row){
        if (row >= 0 && row < HEIGHT){
            this.player[ROW] = row;
        }
    }
    public void setPlayerCol(int col){
        if (col >= 0 && col < WIDTH){
            this.player[COL] = col;
        }
    }

    public void setGoalRow(int row){
        if (row >= 0 && row < HEIGHT){
            this.goal[ROW] = row;
        }
    }
    public void setGoalCol(int col){
        if (col >= 0 && col < WIDTH){
            this.goal[COL] = col;
        }
    }

    public void setStartRow(int row){
        if (row >= 0 && row < HEIGHT){
            this.start[ROW] = row;
        }
    }
    public void setStartCol(int col){
        if (col >= 0 && col < WIDTH){
            this.start[COL] = col;
        }
    }

    private boolean[][] copyTwoDimBoolArray(boolean[][] arrayToCopy){
        boolean[][] copy = new boolean[arrayToCopy.length][];
        for (int i = 0; i < arrayToCopy.length; i++){
            copy[i] = arrayToCopy[i].clone();
        }
        return copy;
    }

    public void setBlocked(boolean[][] blocked){
        this.blocked = copyTwoDimBoolArray(blocked);
    }
    public void setVisited(boolean[][] visited){
        this.visited = copyTwoDimBoolArray(visited);
    }

    public void setPlayerInput(Scanner playerInput){
        this.playerInput = playerInput;
    }

    private void prompt(){
        printMaze();
        System.out.print("Enter your move (up, down, left, right, or q to quit): ");
    }

    private boolean playerAtGoal(){
        return player[ROW] == goal[ROW] && player[COL] == goal[COL];
    }

    private boolean valid(int row, int col){
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH && !blocked[row][col];
    }

    private void visit(int row, int col){
        visited[row][col] = true;
    }

    private void loadMaze(String mazeFile) throws FileNotFoundException{
        blocked = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        player = new int[2];
        goal = new int[2];
        start = new int[2];

        File file = new File(mazeFile);
        Scanner kb = new Scanner(file);
        for (int i = 0; i < HEIGHT; i++){
                        
            for (int j = 0; j < WIDTH; j++){
                String readNext = kb.next();
                char tile = readNext.charAt(0);
                switch (tile){
                    case '1':
                    blocked[i][j] = true;
                    break;

                    case '0':
                    blocked[i][j] = false;
                    break;

                    case 'S':
                    start[ROW] = i;
                    start[COL] = j;
                    player[ROW] = i;
                    player[COL] = j;
                    break;

                    case 'G':
                    goal[ROW] = i;
                    goal[COL] = j;
                    break;

                    default:
                    break;
                }
                visited[i][j] = false;
        }
        
    }
    kb.close();  
    }

    private boolean makeMove(String move){
        char first = move.toLowerCase().charAt(0);

        switch (first){
            case 'q':
            return true;

            case 'u':
            if (valid(player[ROW] - 1, player[COL])) {
                setPlayerRow(player[ROW] - 1);
                visit(player[ROW], player[COL]);
            }
            break;

            case 'd':
            if (valid(player[ROW] + 1, player[COL])) {
                setPlayerRow(player[ROW] + 1);
                visit(player[ROW], player[COL]);
            }
            break;

            case 'l':
            if (valid(player[ROW], player[COL] - 1)) {
                setPlayerCol(player[COL] - 1);
                visit(player[ROW], player[COL]);
            }
            break;

            case 'r':
            if (valid(player[ROW], player[COL] + 1)) {
                setPlayerCol(player[COL] + 1);
                visit(player[ROW], player[COL]);
            }
            break;

            default:
            break;

        }
        return playerAtGoal();
    }
}