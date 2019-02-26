/*
Name: Animesh Chaudhry
CIS 18B
Final: Chess
06/06/2018
*/
package com.company;

import java.util.Scanner;


public class Main{


    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to a game of Chess!");
        GameManagerSingleton sig = GameManagerSingleton.getInstance();//The one and only instance of the singleton
        sig.initializegame();//Start the game with the one singleton instance


        //Pieces[][] chessboard = new Pieces[8][8]; // creates chessboard of size 8 x 8
        String move_enter = "";
        PieceFactory pieceFactory = new PieceFactory();
        pieceFactory.newpieces();//Put the pieces on the board

        SubjectImplement subject = new SubjectImplement();

        ChessController controller = new ChessController(pieceFactory);

        System.out.println("Input in following way: “letternumber to letternumber, for ex. a7 to a5”:\n");

        //subject.notifyObserver();
        while(true){
            //pieceFactory.printBoard();
            controller.updateview();
            if(GameManagerSingleton.currentPlayer == GameManagerSingleton.PLAYER_1){
                GameManagerSingleton.currentPlayer = GameManagerSingleton.PLAYER_2;
                //subject.notifyObserver();

            }
            else {
                GameManagerSingleton.currentPlayer = GameManagerSingleton.PLAYER_1;
            }
            move_enter = sc.nextLine();

            if (move_enter.equals("exit")){
                System.exit(1);
            }

            pieceFactory.move(move_enter);

        }

    }



}


