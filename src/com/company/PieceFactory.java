package com.company;

public class PieceFactory extends ChessEnum {

    Pieces[][] board = new Pieces[8][8];//Create

    public void newpieces() {
       PieceGenerate newlocation = null;

        for (int i = 0; i < 8; i++) { // Initialize the board
            for (int j = 0; j < 8; j++) { //The size of the board is 8 x 8

                if (i == 0) {
                    switch (j) { //Switch cases for putting pieces at the right locations
                        case 0: // 0 and 7 positions when i = 0 for 2 Black Rooks
                        case 7:
                            board[i][j] = Pieces.BRook;
                            break;
                        case 1: // 1 and 6 positions when i = 0 for 2 Black knights
                        case 6:
                            board[i][j] = Pieces.BKnight;
                            break;
                        case 2: // 2 and 5 positions when i = 0 for 2 Black Bishops
                        case 5:
                            board[i][j] = Pieces.BBishop;
                            break;
                        case 3:  // 3 position when i = 0 for one Black Queen
                            board[i][j] = Pieces.BQueen;
                            break;
                        case 4: // 4 position when i = 0 for one Black Queen
                            board[i][j] = Pieces.BKing;
                            break;
                    }
                } else if (i == 1) {
                    board[i][j] = Pieces.BPawn; //Black pawn at 1,j
                } else if (i > 1 && i < 6) { //All other places until the white pieces start must be empty
                    board[i][j] = Pieces.EMPTY;
                } else if (i == 6) {
                    board[i][j] = Pieces.WPawn; //Black pawns at 6,j
                } else if (i == 7) { //Start filling the white pieces on the board
                    switch (j) { // Similar to the way the black pieces were inserted on the board.
                                 // These white pieces will be visually present on top of the board when playing
                        case 0:
                        case 7:
                            board[i][j] = Pieces.WRook;
                            break;
                        case 1:
                        case 6:
                            board[i][j] = Pieces.WKnight;
                            break;
                        case 2:
                        case 5:
                            board[i][j] = Pieces.WBishop;
                            break;
                        case 3:
                            board[i][j] = Pieces.WQueen;
                            break;
                        case 4:
                            board[i][j] = Pieces.WKing;
                            break;
                    }
                }
            }

        }

    }


    public void printBoard() { //Printing the board on the screen using unicode
        char a = 'a'; //The way I have this laid out is in the format letter then number
                      // For example locations a1, e3, and so on
                      // Start with the letter a and assign it to a spot. The increment
        System.out.print(" ");
        for (int l = 0; l < 8; l++) { //Again size of board is 8
            System.out.print(String.format("%2.9s", a)); // SPace out the letters so they make sense visually
                                                            // Still they are not aligned right on top of pieces
            a++;//increment characters so that we have a through h, uses ascii numbers corresponding to lower case letters
        }
        System.out.println("\r"); // Printing the space between the letters and pieces

        for (int i = 0; i < 8; i++) { //Now assign the actual pieces in unicode
            System.out.print(8-i + " "); //Print the numbers corresponding to the letters

            for (int j = 0; j < 8; j++) { //8 x 8 size

                switch (board[i][j]) { //Print the pieces on the screen

                    case BKnight:
                        System.out.print("\u265E ");
                        break;
                    case BPawn:
                        System.out.print("\u265F ");
                        break;
                    case BQueen:
                        System.out.print("\u265B ");
                        break;
                    case BKing:
                        System.out.print("\u265A ");
                        break;
                    case BRook:
                        System.out.print("\u265C ");
                        break;
                    case BBishop:
                        System.out.print("\u265D ");
                        break;
                    case WQueen:
                        System.out.print("\u2655 ");
                        break;
                    case WPawn:
                        System.out.print("\u2659 ");
                        break;
                    case WBishop:
                        System.out.print("\u2657 ");
                        break;
                    case WRook:
                        System.out.print("\u2656 ");
                        break;
                    case WKing:
                        System.out.print("\u2654 ");
                        break;
                    case WKnight:
                        System.out.print("\u2658 ");
                        break;
                    case EMPTY:
                        System.out.print(" ");
                        break;
                }
            }
            System.out.println("\r");
        }
    }




    public void move(String move) { // Actual logic of the game starts here
        // parse the string into components
        //The format of the user input is the following: letternumber to letternumber
        // For example e3 to e6 or something like that
        String[] components = move.split(" "); //Split the string and store in a component array

        // The length of the components (there will be 3) should not be greater than 3
        if (components.length > 3) {
            System.err.println("\rNot a valid length of the components!");
        } else if (components[0].length() != 2 || components[2].length() != 2) { // Length of the each of them must be at least 2
            System.err.println("\rLength of each of the components must be 2!");//Length 2 only
        } else if (components[0].charAt(0) < 'a' || components[0].charAt(0) > 'h' || components[0].charAt(1) < '1' || components[0].charAt(1) > '8') {
            System.err.println("\rLettes are only from a through h (Lowercase)!"); //Letters possible a - h
        } else if (components[2].charAt(0) < 'a' || components[2].charAt(0) > 'h' || components[2].charAt(1) < '1' || components[2].charAt(1) > '8') {
            System.err.println("\rLettes are only from a through h (Lowercase)!"); //Same comment as the previous one
        } else {

            int col = components[0].charAt(0) - 97; //Replacing the original position with the piece, player makes the move
                                                        //calculating the new row value
            int row = Math.abs(components[0].charAt(1) - 49 - 7); // new column value, need absolute value function

            //calculate the new row and the new column AFTER the move has been made
            int newClCol = components[2].charAt(0) - 97;
            int newClRow = Math.abs(components[2].charAt(1) - 49 - 7);

            System.out.println(col + " " + row + " " + newClCol + " " + newClRow);
            if (ValidateMove(board, row, col, newClRow, newClCol)) { //Check if the operation is valid
                board[newClRow][+newClCol] = board[row][+col]; //Pieces being handled
                board[row][+col] = Pieces.EMPTY; //The old position is now empty
            } else {
                System.err.println("Invalid Entry");
            }

        }
    }

    public Boolean ValidateMove(Pieces[][] board, int row, int col, int newClRow, int newClCol) {

        // check if the move is valid
        //Return either true or false
        //For all pieces
        switch (board[row][col]) {

            case BKnight:
            case WKnight:
                break;
            case BBishop:
            case WBishop:
                break;
            case BQueen:
                if ((board[newClRow][newClCol] == Pieces.WRook || board[newClRow][newClCol] == Pieces.WKnight ||
                        board[newClRow][newClCol] == Pieces.WBishop ||
                        board[newClRow][newClCol] == Pieces.WQueen || board[newClRow][newClCol] == Pieces.WKing || board[newClRow][newClCol] == Pieces.EMPTY) && (newClCol == col || newClRow == row || newClCol == col - 1 || newClRow == row + 1 || newClRow == row || newClRow == row - 1)) {
                    return true;
                }
                break;
            case WQueen:
                break;
            case BRook:
                if ((board[newClRow][newClCol] == Pieces.WRook || board[newClRow][newClCol] == Pieces.WKnight ||
                        board[newClRow][newClCol] == Pieces.WBishop || board[newClRow][newClCol] == Pieces.WQueen ||
                        board[newClRow][newClCol] == Pieces.WKing || board[newClRow][newClCol] == Pieces.EMPTY)
                        && (newClCol == col || newClRow == row)) {
                    return true;
                }
                break;
            case WRook:
                if ((board[newClRow][newClCol] == Pieces.BRook || board[newClRow][newClCol] == Pieces.BKnight ||
                        board[newClRow][newClCol] == Pieces.BBishop || board[newClRow][newClCol] == Pieces.BQueen ||
                        board[newClRow][newClCol] == Pieces.BKing || board[newClRow][newClCol] == Pieces.EMPTY)
                        && (newClCol == col || newClRow == row)) {
                    return true;
                }
                break;

            case BKing:
                if ((board[newClRow][newClCol] == Pieces.WRook || board[newClRow][newClCol] == Pieces.WKnight ||
                        board[newClRow][newClCol] == Pieces.WBishop || board[newClRow][newClCol] == Pieces.WQueen ||
                        board[newClRow][newClCol] == Pieces.WKing || board[newClRow][newClCol] == Pieces.EMPTY)
                        && ((newClCol == col + 1 || newClCol == col || newClCol == col - 1) && (newClRow == row + 1 || newClRow == row || newClRow == row - 1))) {
                    return true;
                }
                break;
            case WKing:
                if ((board[newClRow][newClCol] == Pieces.BRook || board[newClRow][newClCol] == Pieces.BKnight ||
                        board[newClRow][newClCol] == Pieces.BBishop || board[newClRow][newClCol] == Pieces.BQueen ||
                        board[newClRow][newClCol] == Pieces.BKing || board[newClRow][newClCol] == Pieces.EMPTY)
                        && ((newClCol == col + 1 || newClCol == col || newClCol == col - 1) && (newClRow == row + 1 || newClRow == row || newClRow == row - 1))) {
                    return true;
                }
                break;

            case BPawn:
                if (board[newClRow][newClCol] != Pieces.EMPTY && (newClCol == col + 1 || newClCol == col - 1)) {
                    return true;
                } else if (board[newClRow][newClCol] != Pieces.EMPTY) {
                    return false;
                } else if (row + 1 == newClRow || (row == 1 && row + 2 == newClRow)) {
                    return true;
                }
                break;
            case WPawn:
                if (board[newClRow][newClCol] != Pieces.EMPTY && (newClCol == col + 1 || newClCol == col - 1)) {
                    return true;
                } else if (board[newClRow][newClCol] != Pieces.EMPTY) {
                    return false;
                } else if (row - 1 == newClRow || (row == 6 && row - 2 == newClRow)) {
                    return true;
                }
                break;
            case EMPTY:
                return false;
        }
        return false;

    }



}
