import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class BoardGame extends Sprite {
    private String currentSide;
    private String winStatus = "";
    private boolean gameOverSoundPlayed = false;
    private Piece selectedPiece;
    private ArrayList<Pos> selectedPaths = new ArrayList<Pos>();

    private ArrayList<Piece> redPieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();

    private Board board;

    public BoardGame(String StartSide) {
        currentSide = StartSide;
        this.board = new Board(10, 9, StartSide);
        board.initialPieces();
    }   

    public class Piece {
        private String type;
        private String side;

        public Piece(String type, String side ) {
            this.type = type;
            this.side = side;
        }
    
        public String getType() {
            return type;
        }

        public String getSide() {
            return side;
        }
    }

    public class Pos {
        private int row;
        private int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    public ArrayList<Pos> returnPaths(Piece piece, Board board) {
        ArrayList<Pos> paths = new ArrayList<Pos>();
        int pieceRow = board.getPieceRow(piece);
        int pieceCol = board.getPieceCol(piece);

        // Implement movement rules for each piece type
        // For example, for a "車" (Rook), it can move any number of spaces horizontally or vertically
        if (piece.getType().equals("車")) {
            addLineMoves(paths, piece, board, -1, 0);
            addLineMoves(paths, piece, board, 1, 0);
            addLineMoves(paths, piece, board, 0, -1);
            addLineMoves(paths, piece, board, 0, 1);
        }

        if (piece.getType().equals("馬")) {
            // Add L-shaped paths for the "馬" (Knight)
            int[][] knightMoves = {
                {-2, -1, -1, 0}, {-2, 1, -1, 0},
                {-1, -2, 0, -1}, {1, -2, 0, -1},
                {-1, 2, 0, 1}, {1, 2, 0, 1},
                {2, -1, 1, 0}, {2, 1, 1, 0}
            };
            for (int[] move : knightMoves) {
                int newRow = pieceRow + move[0];
                int newCol = pieceCol + move[1];
                int legRow = pieceRow + move[2];
                int legCol = pieceCol + move[3];
                if (board.isValidPosition(newRow, newCol) && board.getPiece(legRow, legCol) == null) {
                    addIfEmptyOrEnemy(paths, piece, board, newRow, newCol);
                }
            }
        }

        if (piece.getType().equals("象")) {
            // Add diagonal paths for the "象" (Elephant), but it cannot cross the river
            int[][] elephantMoves = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};
            for (int[] move : elephantMoves) {
                int newRow = pieceRow + move[0];
                int newCol = pieceCol + move[1];
                int eyeRow = pieceRow + move[0] / 2;
                int eyeCol = pieceCol + move[1] / 2;
                if (board.isValidPosition(newRow, newCol) && board.getPiece(eyeRow, eyeCol) == null) {
                    // Check if the move crosses the river
                    if ((piece.getSide().equals("red") && newRow >= 5) || (piece.getSide().equals("black") && newRow <= 4)) {
                        addIfEmptyOrEnemy(paths, piece, board, newRow, newCol);
                    }
                }
            }
        }

        if (piece.getType().equals("士")) {
            // Add diagonal paths for the "士" (Advisor), but it must stay within the palace
            int[][] advisorMoves = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
            for (int[] move : advisorMoves) {
                int newRow = pieceRow + move[0];
                int newCol = pieceCol + move[1];
                if (board.isValidPosition(newRow, newCol)) {
                    // Check if the move is within the palace
                    if ((piece.getSide().equals("red") && newRow >= 7 && newCol >= 3 && newCol <= 5) || 
                        (piece.getSide().equals("black") && newRow <= 2 && newCol >= 3 && newCol <= 5)) {
                        addIfEmptyOrEnemy(paths, piece, board, newRow, newCol);
                    }
                }
            }
        }

        if (piece.getType().equals("将")) {
            // Add one-space moves for the "将" (General), but it must stay within the palace
            int[][] generalMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] move : generalMoves) {
                int newRow = pieceRow + move[0];
                int newCol = pieceCol + move[1];
                if (board.isValidPosition(newRow, newCol)) {
                    // Check if the move is within the palace
                    if ((piece.getSide().equals("red") && newRow >= 7 && newCol >= 3 && newCol <= 5) || 
                        (piece.getSide().equals("black") && newRow <= 2 && newCol >= 3 && newCol <= 5)) {
                        addIfEmptyOrEnemy(paths, piece, board, newRow, newCol);
                    }
                }
            }
            //if the opponent's General is in the same column and there are no pieces in between, the General can move to capture the opponent's General
            for (int i = 0; i < board.MAXROW; i++) {
                if (board.getPiece(i, board.getPieceCol(piece)) != null && 
                    board.getPiece(i, board.getPieceCol(piece)).getType().equals("将") && 
                    !board.getPiece(i, board.getPieceCol(piece)).getSide().equals(piece.getSide())) {
                    boolean pathClear = true;
                    int step = (i > board.getPieceRow(piece)) ? 1 : -1;
                    for (int j = board.getPieceRow(piece) + step; j != i; j += step) {
                        if (board.getPiece(j, board.getPieceCol(piece)) != null) {
                            pathClear = false;
                            break;
                        }
                    }
                    if (pathClear) {
                        paths.add(new Pos(i, board.getPieceCol(piece)));
                    }
                }
            }
        }

        if (piece.getType().equals("炮")) {
            addCannonMoves(paths, piece, board, -1, 0);
            addCannonMoves(paths, piece, board, 1, 0);
            addCannonMoves(paths, piece, board, 0, -1);
            addCannonMoves(paths, piece, board, 0, 1);
        }

        if (piece.getType().equals("兵") || piece.getType().equals("卒")) {
            // Add forward moves for the "兵" (Soldier) and "卒" (Pawn), and sideways moves after crossing the river
            int direction = piece.getSide().equals("red") ? -1 : 1; // Red moves up, Black moves down
            int newRow = pieceRow + direction;
            addIfEmptyOrEnemy(paths, piece, board, newRow, pieceCol);
            // Check for sideways moves after crossing the river
            if ((piece.getSide().equals("red") && pieceRow < 5) || 
                (piece.getSide().equals("black") && pieceRow >= 5)) {
                addIfEmptyOrEnemy(paths, piece, board, pieceRow, pieceCol - 1);
                addIfEmptyOrEnemy(paths, piece, board, pieceRow, pieceCol + 1);
            }
        }

        if (piece.getType().equals("将")) {
            // Add the "Flying General" rule, where the General can move to the opponent's General if there are no pieces in between
            int opponentGeneralRow = -1;
            int opponentGeneralCol = -1;
            for (int i = 0; i < board.MAXROW; i++) {
                for (int j = 0; j < board.MAXCOL; j++) {
                    Piece p = board.getPiece(i, j);
                    if (p != null && p.getType().equals("将") && !p.getSide().equals(piece.getSide())) {
                        opponentGeneralRow = i;
                        opponentGeneralCol = j;
                        break;
                    }
                }
                if (opponentGeneralRow != -1) {
                    break;
                }
            }
            if (opponentGeneralRow != -1 && opponentGeneralCol == board.getPieceCol(piece)) {
                boolean pathClear = true;
                int step = (opponentGeneralRow > board.getPieceRow(piece)) ? 1 : -1;
                for (int i = board.getPieceRow(piece) + step; i != opponentGeneralRow; i += step) {
                    if (board.getPiece(i, opponentGeneralCol) != null) {
                        pathClear = false;
                        break;
                    }
                }
                if (pathClear) {
                    paths.add(new Pos(opponentGeneralRow, opponentGeneralCol));
                }
            }
        }

        // Implement other piece types similarly
        return paths;
    }

    private void addIfEmptyOrEnemy(ArrayList<Pos> paths, Piece piece, Board board, int row, int col) {
        if (!board.isValidPosition(row, col)) {
            return;
        }

        Piece target = board.getPiece(row, col);
        if (target == null || !target.getSide().equals(piece.getSide())) {
            paths.add(new Pos(row, col));
        }
    }

    private void addLineMoves(ArrayList<Pos> paths, Piece piece, Board board, int rowStep, int colStep) {
        int row = board.getPieceRow(piece) + rowStep;
        int col = board.getPieceCol(piece) + colStep;

        while (board.isValidPosition(row, col)) {
            Piece target = board.getPiece(row, col);
            if (target == null) {
                paths.add(new Pos(row, col));
            } else {
                if (!target.getSide().equals(piece.getSide())) {
                    paths.add(new Pos(row, col));
                }
                break;
            }

            row += rowStep;
            col += colStep;
        }
    }

    private void addCannonMoves(ArrayList<Pos> paths, Piece piece, Board board, int rowStep, int colStep) {
        int row = board.getPieceRow(piece) + rowStep;
        int col = board.getPieceCol(piece) + colStep;
        boolean jumpedPiece = false;

        while (board.isValidPosition(row, col)) {
            Piece target = board.getPiece(row, col);

            if (!jumpedPiece) {
                if (target == null) {
                    paths.add(new Pos(row, col));
                } else {
                    jumpedPiece = true;
                }
            } else if (target != null) {
                if (!target.getSide().equals(piece.getSide())) {
                    paths.add(new Pos(row, col));
                }
                break;
            }

            row += rowStep;
            col += colStep;
        }
    }

    public void graphicsPieces(String pieceSide , String piece , Graphics g, int row, int col) {
        Color color = (pieceSide.equals("red")) ? new Color(222, 26, 26) : new Color(0, 0, 0);
        Font body = new Font("Arial", Font.PLAIN, 20);
        int cellSize = getCellSize();
        int x = getBoardOffsetX() + col * cellSize;
        int y = getBoardOffsetY() + row * cellSize;
        g.setColor(new Color(250, 224, 160));
        g.fillOval(x + 3, y + 3, cellSize - 6, cellSize - 6);
        g.setColor(color);
        g.drawOval(x + 3, y + 3, cellSize - 6, cellSize - 6);
        g.setFont(body);
        g.drawString(piece, x + 20, y + 38);
    }

    public void movePiece(Piece piece, Pos newPos, Board board) {
        // Implement the logic to move a piece to a new position on the board
        // This should include checking if the move is valid based on the piece's movement rules and if it captures an opponent's piece
        ArrayList<Pos> validPaths = returnPaths(piece, board);
        boolean isValidMove = false;
        for (Pos pos : validPaths) {
            if (pos.getRow() == newPos.getRow() && pos.getCol() == newPos.getCol()) {
                isValidMove = true;
                break;
            }
        }
        if (isValidMove) {
            Piece capturedPiece = board.getPiece(newPos.getRow(), newPos.getCol());
            if (capturedPiece != null) {
                // Capture the opponent's piece
                if (capturedPiece.getSide().equals("red")) {
                    redPieces.remove(capturedPiece);
                } else {
                    blackPieces.remove(capturedPiece);
                }
            }
            // Move the piece to the new position
            board.movePiece(board.getPieceRow(piece), board.getPieceCol(piece), newPos.getRow(), newPos.getCol());
            java.awt.Toolkit.getDefaultToolkit().beep();
            selectedPiece = null;
            selectedPaths.clear();
            updateTurn();
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (!inGame) {
            return;
        }

        int cellSize = getCellSize();
        int boardLeft = getBoardOffsetX();
        int boardTop = getBoardOffsetY();

        if (mouseX < boardLeft || mouseY < boardTop
                || mouseX >= boardLeft + board.MAXCOL * cellSize
                || mouseY >= boardTop + board.MAXROW * cellSize) {
            selectedPiece = null;
            selectedPaths.clear();
            return;
        }

        int row = (mouseY - boardTop) / cellSize;
        int col = (mouseX - boardLeft) / cellSize;

        if (!board.isValidPosition(row, col)) {
            selectedPiece = null;
            selectedPaths.clear();
            return;
        }

        Piece clickedPiece = board.getPiece(row, col);
        if (clickedPiece != null && clickedPiece.getSide().equals(currentSide)) {
            selectedPiece = clickedPiece;
            selectedPaths = returnPaths(clickedPiece, board);
            return;
        }

        if (selectedPiece != null) {
            for (Pos pos : selectedPaths) {
                if (pos.getRow() == row && pos.getCol() == col) {
                    movePiece(selectedPiece, pos, board);
                    checkWinCondition(board);
                    return;
                }
            }
        }

        selectedPiece = null;
        selectedPaths.clear();
    }

    public void checkWinCondition(Board board) {
        if (!winStatus.equals("")) {
            return;
        }

        // Implement the logic to check if either player has won the game
        // This typically involves checking if the opponent's General has been captured
        boolean redGeneralCaptured = true;
        boolean blackGeneralCaptured = true;

        for (Piece piece : redPieces) {
            if (piece.getType().equals("将")) {
                redGeneralCaptured = false;
                break;
            }
        }

        for (Piece piece : blackPieces) {
            if (piece.getType().equals("将")) {
                blackGeneralCaptured = false;
                break;
            }
        }

        if (redGeneralCaptured) {
            winStatus = "Black ";
            playWinningSoundOnce();
            updateInGame(false);
        } else if (blackGeneralCaptured) {
            winStatus = "Red ";
            playWinningSoundOnce();
            updateInGame(false);
        } else {
            winStatus = "";
        }
    }

    private void playWinningSoundOnce() {
        if (!gameOverSoundPlayed) {
            winningSound();
            gameOverSoundPlayed = true;
        }
    }

    public class Board {
        private Piece[][] board;
        private int MAXROW = 10;
        private int MAXCOL = 9;

        public Board(int row , int col) {
            MAXROW = row;
            MAXCOL = col;
            board = new Piece[MAXROW][MAXCOL];
        }

        public Board(int row , int col, String pieceSide) {
            MAXROW = row;
            MAXCOL = col;
            board = new Piece[MAXROW][MAXCOL];
        }

        private boolean isValidPosition(int row, int col) {
            return row >= 0 && row < MAXROW && col >= 0 && col < MAXCOL;
        }

        public void initialPieces() {
            for (int row = 0; row < MAXROW; row++) {
                for (int col = 0; col < MAXCOL; col++) {
                    board[row][col] = null;
                }
            }

            redPieces.clear();
            blackPieces.clear();

            // Initialize pieces for both sides. Black starts at top, red starts at bottom.
            for (int col = 0; col < MAXCOL; col+=2) {
                placePiece(3, col, "卒", "black");
                placePiece(6, col, "兵", "red");
            }

            placePiece(0, 0, "車", "black");
            placePiece(0, 1, "馬", "black");
            placePiece(0, 2, "象", "black");
            placePiece(0, 3, "士", "black");
            placePiece(0, 4, "将", "black");
            placePiece(0, 5, "士", "black");
            placePiece(0, 6, "象", "black");
            placePiece(0, 7, "馬", "black");
            placePiece(0, 8, "車", "black");

            placePiece(2, 1, "炮", "black");
            placePiece(2, 7, "炮", "black");

            placePiece(9, 0, "車", "red");
            placePiece(9, 1, "馬", "red");
            placePiece(9, 2, "象", "red");
            placePiece(9, 3, "士", "red");
            placePiece(9, 4, "将", "red");
            placePiece(9, 5, "士", "red");
            placePiece(9, 6, "象", "red");
            placePiece(9, 7, "馬", "red");
            placePiece(9, 8, "車", "red");

            placePiece(7, 1, "炮", "red");
            placePiece(7, 7, "炮", "red");
        }

        private void placePiece(int row, int col, String type, String side) {
            Piece piece = new Piece(type, side);
            board[row][col] = piece;
            if (side.equals("red")) {
                redPieces.add(piece);
            } else {
                blackPieces.add(piece);
            }
        }

        public int getPieceRow(Piece piece) {
            for (int i = 0; i < MAXROW; i++) {
                for (int j = 0; j < MAXCOL; j++) {
                    if (board[i][j] == piece) {
                        return i;
                    }
                }
            }
            return -1; // Piece not found
        }

        public int getPieceCol(Piece piece) {
            for (int i = 0; i < MAXROW; i++) {
                for (int j = 0; j < MAXCOL; j++) {
                    if (board[i][j] == piece) {
                        return j;
                    }
                }
            }
            return -1; // Piece not found
        }

        public Piece getPiece(int row, int col) {
            if (isValidPosition(row, col)) {
                return board[row][col];
            }
            return null;
        }

        public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
            if (isValidPosition(fromRow, fromCol) && isValidPosition(toRow, toCol)) {
                Piece piece = board[fromRow][fromCol];
                board[toRow][toCol] = piece;
                board[fromRow][fromCol] = null;
            }
        }

    }

    private Boolean inGame = false;


    public void drawGame(Graphics g, BoardGame boardGame) {
        if (inGame) {
            drawXiangqiBoard(g);
            drawSelectedPieceHighlight(g);
            Piece piece;
            String pieceSide;
            for (int row = 0; row < board.MAXROW; row++) {
                for (int col = 0; col < board.MAXCOL; col++) {
                    piece = board.getPiece(row, col);
                    if (piece != null) {
                        pieceSide = piece.getSide();
                        graphicsPieces(pieceSide, piece.getType(), g, row, col);
                    }
                }
            }
            drawSelectedPaths(g);
        
            checkWinCondition(board);
        }
    }

    private void drawSelectedPieceHighlight(Graphics g) {
        int cellSize = getCellSize();

        if (selectedPiece != null) {
            int selectedRow = board.getPieceRow(selectedPiece);
            int selectedCol = board.getPieceCol(selectedPiece);
            int x = getBoardOffsetX() + selectedCol * cellSize;
            int y = getBoardOffsetY() + selectedRow * cellSize;
            g.setColor(new Color(255, 245, 120));
            g.fillOval(x + 7, y + 7, cellSize - 14, cellSize - 14);
        }
    }

    private void drawSelectedPaths(Graphics g) {
        int cellSize = getCellSize();

        for (Pos pos : selectedPaths) {
            Piece target = board.getPiece(pos.getRow(), pos.getCol());
            int x = getBoardOffsetX() + pos.getCol() * cellSize;
            int y = getBoardOffsetY() + pos.getRow() * cellSize;

            if (target == null) {
                g.setColor(new Color(60, 120, 70));
                g.fillOval(x + cellSize / 2 - 6, y + cellSize / 2 - 6, 12, 12);
            } else if (selectedPiece != null && !target.getSide().equals(selectedPiece.getSide())) {
                g.setColor(new Color(190, 35, 25));
                g.drawOval(x + 8, y + 8, cellSize - 16, cellSize - 16);
                g.drawOval(x + 9, y + 9, cellSize - 18, cellSize - 18);
            }
        }
    }

    public String getWinStatus() {
        return winStatus;
    }

    public Boolean getGameState() {
        return inGame;
    } 

    public void updateInGame(Boolean state) {
        inGame = state;
    }

    public void updateTurn() {
        currentSide = (currentSide.equals("red") ) ? "black" : "red";
    }

    public void setForceQuit() {
        if (inGame && winStatus.equals("")) {
            playWinningSoundOnce();
        }
        winStatus = "exited";
        updateInGame(false);
    }

}
