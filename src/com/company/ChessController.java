package com.company;

public class ChessController {

    private PieceFactory pieces;


    public ChessController(PieceFactory pieces) {
        this.pieces = pieces;
    }

    public void updateview() {

        pieces.printBoard();
    }

}