import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class BoardGame extends Sprite {
    private String pieceSide;
    private String winStatus = "";

    private ArrayList<Piece> redPieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();

    private Board board;

    public BoardGame(String pieceSide) {
        this.pieceSide = pieceSide;
        this.board = new Board(10, 9, pieceSide);
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

        // Implement movement rules for each piece type
        // For example, for a "車" (Rook), it can move any number of spaces horizontally or vertically
        if (piece.getType().equals("車")) {
            // Add horizontal and vertical paths
            for (int i = 0; i < board.MAXROW; i++) {
                if (board.getPiece(i, board.getPieceCol(piece)) == null) {
                    paths.add(new Pos(i, board.getPieceCol(piece)));
                }
            }
            for (int j = 0; j < board.MAXCOL; j++) {
                if (j != board.getPieceCol(piece)) {
                    paths.add(new Pos(board.getPieceRow(piece), j));
                }
            }
        }

        if (piece.getType().equals("馬")) {
            // Add L-shaped paths for the "馬" (Knight)
            int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
            for (int[] move : knightMoves) {
                int newRow = board.getPieceRow(piece) + move[0];
                int newCol = board.getPieceCol(piece) + move[1];
                if (board.isValidPosition(newRow, newCol) && board.getPiece(newRow, newCol) == null) {
                    paths.add(new Pos(newRow, newCol));
                }
            }
        }

        if (piece.getType().equals("象")) {
            // Add diagonal paths for the "象" (Elephant), but it cannot cross the river
            int[][] elephantMoves = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};
            for (int[] move : elephantMoves) {
                int newRow = board.getPieceRow(piece) + move[0];
                int newCol = board.getPieceCol(piece) + move[1];
                if (board.isValidPosition(newRow, newCol) && board.getPiece(newRow, newCol) == null) {
                    // Check if the move crosses the river
                    if ((piece.getSide().equals("red") && newRow < 5) || (piece.getSide().equals("black") && newRow >= 5)) {
                        paths.add(new Pos(newRow, newCol));
                    }
                }
            }
        }

        if (piece.getType().equals("士")) {
            // Add diagonal paths for the "士" (Advisor), but it must stay within the palace
            int[][] advisorMoves = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
            for (int[] move : advisorMoves) {
                int newRow = board.getPieceRow(piece) + move[0];
                int newCol = board.getPieceCol(piece) + move[1];
                if (board.isValidPosition(newRow, newCol) && board.getPiece(newRow, newCol) == null) {
                    // Check if the move is within the palace
                    if ((piece.getSide().equals("red") && newRow >= 7 && newCol >= 3 && newCol <= 5) || 
                        (piece.getSide().equals("black") && newRow <= 2 && newCol >= 3 && newCol <= 5)) {
                        paths.add(new Pos(newRow, newCol));
                    }
                }
            }
        }

        if (piece.getType().equals("将")) {
            // Add one-space moves for the "将" (General), but it must stay within the palace
            int[][] generalMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] move : generalMoves) {
                int newRow = board.getPieceRow(piece) + move[0];
                int newCol = board.getPieceCol(piece) + move[1];
                if (board.isValidPosition(newRow, newCol) && board.getPiece(newRow, newCol) == null) {
                    // Check if the move is within the palace
                    if ((piece.getSide().equals("red") && newRow >= 7 && newCol >= 3 && newCol <= 5) || 
                        (piece.getSide().equals("black") && newRow <= 2 && newCol >= 3 && newCol <= 5)) {
                        paths.add(new Pos(newRow, newCol));
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
            // Add horizontal and vertical paths for the "炮" (Cannon), but it must jump over exactly one piece to capture
            for (int i = 0; i < board.MAXROW; i++) {
                if (board.getPiece(i, board.getPieceCol(piece)) == null) {
                    paths.add(new Pos(i, board.getPieceCol(piece)));
                } else if (board.getPiece(i, board.getPieceCol(piece)) != piece) {
                    // Check for a piece to jump over
                    for (int j = i + 1; j < board.MAXROW; j++) {
                        if (board.getPiece(j, board.getPieceCol(piece)) != null) {
                            if (!board.getPiece(j, board.getPieceCol(piece)).getSide().equals(piece.getSide())) {
                                paths.add(new Pos(j, board.getPieceCol(piece)));
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            for (int j = 0; j < board.MAXCOL; j++) {
                if (j != board.getPieceCol(piece)) {
                    if (board.getPiece(board.getPieceRow(piece), j) == null) {
                        paths.add(new Pos(board.getPieceRow(piece), j));
                    } else if (board.getPiece(board.getPieceRow(piece), j) != piece) {
                        // Check for a piece to jump over
                        for (int k = j + 1; k < board.MAXCOL; k++) {
                            if (board.getPiece(board.getPieceRow(piece), k) != null) {
                                if (!board.getPiece(board.getPieceRow(piece), k).getSide().equals(piece.getSide())) {
                                    paths.add(new Pos(board.getPieceRow(piece), k));
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

        if (piece.getType().equals("兵") || piece.getType().equals("卒")) {
            // Add forward moves for the "兵" (Soldier) and "卒" (Pawn), and sideways moves after crossing the river
            int direction = piece.getSide().equals("red") ? -1 : 1; // Red moves up, Black moves down
            int newRow = board.getPieceRow(piece) + direction;
            if (board.isValidPosition(newRow, board.getPieceCol(piece)) && board.getPiece(newRow, board.getPieceCol(piece)) == null) {
                paths.add(new Pos(newRow, board.getPieceCol(piece)));
            }
            // Check for sideways moves after crossing the river
            if ((piece.getSide().equals("red") && board.getPieceRow(piece) < 5) || 
                (piece.getSide().equals("black") && board.getPieceRow(piece) >= 5)) {
                int newColLeft = board.getPieceCol(piece) - 1;
                int newColRight = board.getPieceCol(piece) + 1;
                if (board.isValidPosition(board.getPieceRow(piece), newColLeft) && board.getPiece(board.getPieceRow(piece), newColLeft) == null) {
                    paths.add(new Pos(board.getPieceRow(piece), newColLeft));
                }
                if (board.isValidPosition(board.getPieceRow(piece), newColRight) && board.getPiece(board.getPieceRow(piece), newColRight) == null) {
                    paths.add(new Pos(board.getPieceRow(piece), newColRight));
                }
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
            updateTurn();
        }
    }

    public void checkWinCondition(Board board) {
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
            updateInGame(false);
        } else if (blackGeneralCaptured) {
            winStatus = "Red ";
            updateInGame(false);
        } else {
            winStatus = "";
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

            // Initialize pieces for both sides
            for (int col = 0; col < MAXCOL; col+=2) {
                placePiece(3, col, "兵", "red");
                placePiece(6, col, "卒", "black");
            }

            placePiece(0, 0, "車", "red");
            placePiece(0, 1, "馬", "red");
            placePiece(0, 2, "象", "red");
            placePiece(0, 3, "士", "red");
            placePiece(0, 4, "将", "red");
            placePiece(0, 5, "士", "red");
            placePiece(0, 6, "象", "red");
            placePiece(0, 7, "馬", "red");
            placePiece(0, 8, "車", "red");

            placePiece(1, 0, "炮", "red");
            placePiece(1, 7, "炮", "red");

            placePiece(9, 0, "車", "black");
            placePiece(9, 1, "馬", "black");
            placePiece(9, 2, "象", "black");
            placePiece(9, 3, "士", "black");
            placePiece(9, 4, "将", "black");
            placePiece(9, 5, "士", "black");
            placePiece(9, 6, "象", "black");
            placePiece(9, 7, "馬", "black");
            placePiece(9, 8, "車", "black");

            placePiece(8, 1, "炮", "black");
            placePiece(8, 7, "炮", "black");
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


    public void drawGame(Graphics g) {
        if (inGame) {
            
        
            checkWinCondition(board);
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
        pieceSide = (pieceSide.equals("red") ) ? "black" : "red";
    }



}