//TC = O(n^2)
//SC = O(n)
class SnakeAndLadder {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return -1;
        int n = board.length;
        int[] arr = new int[n*n];
        int r = n-1;
        int c = 0;
        int idx = 0;
        boolean flag = true; // left to right

        while(idx < arr.length){
            if(board[r][c] == -1){
                arr[idx] = -1;
            } else {
                arr[idx] = board[r][c] - 1;
            }
            idx++;

            if(flag) {
                c++;
                if(c == n){
                    flag = false;
                    r--;
                    c--;
                }
            } else {
                c--;
                if(c < 0) {
                    flag = true;
                    r--;
                    c++;
                }
            }
        }
        // perform bfs
        int moves = 0;
        Queue<Integer> q = new LinkedList<>(); // store index of 1D Array
        q.add(0);
        arr[0] = -2; //Marking it visited
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int currIndex = q.poll();
                if(currIndex >= n*n -1) return moves;
                for(int k = 1; k < 7; k++) { // rolling the dice
                    int newIndex = currIndex + k;
                    if(newIndex == n*n) break;
                    if(arr[newIndex] != -2) {
                        if(arr[newIndex] == -1){
                            q.add(newIndex);
                        } else {
                            q.add(arr[newIndex]);
                        }
                        arr[newIndex] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}