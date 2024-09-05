//Using BFS
//TC = O(m*n)
//SC = O(m*n)
class MineSweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;
        int dirs[][] = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                { -1, -1 } };
        int m = board.length;
        int n = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B'; // Marking it visited

        while (!q.isEmpty()) {
            int[] currEle = q.poll();
            int count = countMines(board, currEle[0], currEle[1], dirs);
            if (count == 0) {
                for (int[] dir : dirs) {
                    int nr = currEle[0] + dir[0];
                    int nc = currEle[1] + dir[1];
                    // bounds check
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        q.add(new int[] { nr, nc });
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[currEle[0]][currEle[1]] = (char) (count + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int r, int c, int[][] dirs) {
        int mines = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            // bounds check
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}