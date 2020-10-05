package tictactoe.bll;

import javafx.scene.control.Button;
import java.util.ArrayList;


/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {
    private boolean turn;
    private final ArrayList<ArrayList<Button>> buttons;

    private boolean strikeX;
    private boolean strikeO;
    private int turnAmount = 0;


    protected GameBoardTwoPlayer() {
        buttons = new ArrayList<ArrayList<Button>>();

        for (int m = 0; m < 3; m++) {
            buttons.add(new ArrayList<Button>());
            for (int n = 0; n < 3; n++) {
                buttons.get(m).add(new Button(""));
            }
        }

        turn = false;
        strikeX = true;
        strikeO = true;
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        if (!turn) {
            return 0;
        } else {
            return 1;
        }
    }
    
    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {
        if (turn && buttons.get(row).get(col).getText().equals("X")) {
            return false;
        }
        if (!turn && buttons.get(row).get(col).getText().equals("O")) {
            return false;
        }
        turnAmount++;
        turn = !turn;
        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        // Check vertically
        for (int m = 0; m < 3; m++) {
            strikeX = true;
            strikeO = true;
            for (int n = 0; n < 3; n++) {
                if (!buttons.get(m).get(n).getText().equals("X"))
                    strikeX = false;
                if (!buttons.get(m).get(n).getText().equals("O"))
                    strikeO = false;
            }
            // Check for strike
            if (strikeX || strikeO) {
                System.out.println("GAME OVER");
                return true;
            }
        }

        // Check horizontally
        for (int n = 0; n < 3; n++) {
            strikeX = true;
            strikeO = true;
            for (int m = 0; m < 3; m++) {
                if (!buttons.get(m).get(n).getText().equals("X"))
                    strikeX = false;
                if (!buttons.get(m).get(n).getText().equals("O"))
                    strikeO = false;
            }
            // Check for strike
            if (strikeX || strikeO) {
                System.out.println("GAME OVER");
                return true;
            }
        }

        // Check diagonally (Top, Down; Left, Right)
        strikeX = true;
        strikeO = true;
        for (int n = 0; n < 3; n++) {
            if (!buttons.get(n).get(n).getText().equals("X"))
                strikeX = false;
            if (!buttons.get(n).get(n).getText().equals("O"))
                strikeO = false;
        }
        // Check strike
        if (strikeX || strikeO) {
            System.out.println("GAME OVER");
            return true;
        }

        // Check diagonally (Top, Down; Right, Left)
        strikeX = true;
        strikeO = true;
        for (int n = 0; n < 3; n++) {
            if (!buttons.get(n).get(2 - n).getText().equals("X"))
                strikeX = false;
            if (!buttons.get(n).get(2 - n).getText().equals("O"))
                strikeO = false;
        }
        // Check strike
        if (strikeX || strikeO) {
            System.out.println("GAME OVER");
            return true;
        }
        if (turnAmount == 9) return true;
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        if (strikeX) {
            return 0;
        }

        if (strikeO) {
            return 1;
        }
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        turnAmount = 0;
    }

    // Get the buttons
    @Override
    public void getPressedButton(Button button, int col, int row) {
        if (buttons.size() > 0)
            buttons.get(row).set(col, button);
    }
}