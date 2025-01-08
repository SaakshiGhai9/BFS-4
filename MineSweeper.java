// Time Complexity O(m * n)
// Space Complexity O(m * n)
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MineSweeper {
    int[][] dirs;
    int m , n;
    public char [][] updateBoard(char[][] board, int [] click){
        this.dirs = new int [][] { {-1,-1}, {-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.m = board.length;
        this.n = board[0].length;
        int row = click[0];
        int col = click[1];

         // if you click oon mine, then game over
        if(board[row][col] =='M'){
            board[row][col] = 'X';
            return board;
        }

        // Initialze the BFS Queue
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int [] {row, col}); // add neighbours
        board [row] [col] = 'B'; // mark as revealed

        // BFS traversal
        while(! queue.isEmpty()){

            int[] cell = queue.poll(); // pop from the queue
            int r = cell[0], c = cell[1];

            // count adjacent mines

            int mineCount = countAdjacentMines(board, r, c );
            if (mineCount != 0){
                // if there is mines nearby , mark the square with the count
                board[r][c] = (char) (mineCount + '0');// reveal the square with number of adjacent mines
            } else{
                // no mines nearby , add all unrevealed neighbours to the queue
                for(int [] dir : dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if ( nr >=0 && nc >=0 && nr <m && nc <n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        queue.add(new int [] {nr,nc}); // add neighbour to the queue
                    }
                }
            }

        }
        return board;
    }

    private int countAdjacentMines(char[][] board, int row, int col){
        int count = 0;
        for( int [] dir : dirs){
            int nr = row + dir [0];
            int nc = col + dir [1];
            if ( nr >=0 && nr <m && nc>=0 && nc<n && board[nr][nc] == 'M'){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MineSweeper game = new MineSweeper();
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click = {3, 0};

        char[][] result = game.updateBoard(board, click);

        for (char[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }

}
