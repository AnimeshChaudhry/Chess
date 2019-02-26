package com.company;

import javax.security.auth.Subject;

/*We have a singleton class here. The one and only object instantiated from this
    class will be the object that is responsible for managing whose turn it is.
    The main purpose of this singleton is to keep track of the state of the game.
*/
public class GameManagerSingleton {



    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int IN_SESSION = 0;

    public static final int PLAYER_1_WIN = 2;
    public static final int PLAYER_2_WIN = 3;
    public static final int DRAW = 1;

    public static final int EMPTY = 0;//The cell is empty
    public static final int FILLED = 1;//The cell is filled
    public static final int ROWS = 8, COLS = 8; // number of rows and columns

    private static GameManagerSingleton unique_manager = null; //One and only instance for this class
    //Need to be available on a global basis

    public static int currentState;  // the current state of the game
    public static int currentPlayer; // the current player
    public static int currntRow, currentCol; // current seed's row and column

    private static boolean firstThread = true;

    private Subject suj;

    private GameManagerSingleton() {
        //Make sure that their is only one instance
        //Make Constructor private
    }



    public static GameManagerSingleton getInstance() {

        if(unique_manager == null) {

            // Deal with time when multiple threads try to create multiple instances of this.
            if(firstThread){
                firstThread = false;
                try {
                    Thread.currentThread();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // Here we just use synchronized when the first object
            // is created
            synchronized(GameManagerSingleton.class){

                if(unique_manager == null) {
                    // Lazy instantiation
                    unique_manager = new GameManagerSingleton();
                }
            }
        }
        // Return the object in either cases, it can be the first time, or return existing object
        return unique_manager;
    }


    public void initializegame() {

        currentPlayer = PLAYER_1; //Start with X
        currentState = IN_SESSION; // Start the game
    }


}






