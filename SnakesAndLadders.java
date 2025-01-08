// Time complexity - O(m * n)
// Space Complexity - o(m* n)
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public int gameMoves(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        boolean flag = true; // this will represent direction of move - true - left to right and F is Right to left
        int r = n - 1, c = 0; // start from bottom left

        // Flatten 2d Array into 1 D array and traverse in a zig zag pattern
        for (int i = 0; i < n * n; i++) {
            if (board[r][c] == -1) { // if no snakes or ladders, copy the board position to the array
                arr[i] = board[r][c];
            } else {
                arr[i] = board[r][c] - 1; // convert 1 based index to 0 based index by subtracting 1
            }
            if (flag) {
                c++;
                if (c == n) {
                    flag = false;
                    c = n - 1;
                    r--;
                }
            } else {
                c--;
                if (c == -1) {
                    flag = true;
                    r--;
                    c = 0;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIndex = queue.poll();
                for (int k = 1; k <= 6; k++) {
                    int nextIndex = currIndex + k;


                    if (nextIndex == n*n - 1 || arr[nextIndex] == n*n - 1) {// if the last square n*n is reached or ladder directly reaches to the last cell
                        return level + 1;   // return level +1 as we add the curent level also the the number of levels
                    }
                    if (arr[nextIndex] != -2) { // not visited
                        if (arr[nextIndex] == -1) {
                            queue.add(nextIndex); // if it does not have snakes or ladders, go to the index directly
                        } else { // if it has snakes or ladders - go to the position pointing to snakes or ladder
                            queue.add(arr[nextIndex]);
                        }
                        arr[nextIndex] = -2; // mark as visited
                    }
                }
            }
            level++;
            }
        return -1;
    }
    public static void main (String [] args){
        SnakesAndLadders solver = new SnakesAndLadders();

        // Example 1
        int[][] board1 = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println(solver.gameMoves(board1)); // Output: 4

        // Example 2
        int[][] board2 = {
                {-1, -1, -1, -1, 48, 5, -1},
                {12, 29, 13, 9, -1, 2, 32},
                {-1, -1, 21, 7, -1, 12, 49},
                {42, 37, 21, 40, -1, 22, 12},
                {42, -1, 2, -1, -1, -1, 6},
                {39, -1, 35, -1, -1, 39, -1},
                {-1, 36, -1, -1, -1, -1, 5}
        };
        System.out.println(solver.gameMoves(board2)); // Output: 3
    }
}



