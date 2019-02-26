package com.company;

public abstract class ChessEnum { //Template

    public enum Pieces {
        WKing, WQueen, WKnight, WPawn, BKing, BQueen, BRook, BBishop, BKnight, BPawn, WRook, WBishop, EMPTY
    }

    abstract void move(String move);
    abstract Boolean ValidateMove(Pieces[][] board, int row, int col, int newClRow, int newClCol);

}
