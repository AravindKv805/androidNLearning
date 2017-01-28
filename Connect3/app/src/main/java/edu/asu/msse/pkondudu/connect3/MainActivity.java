package edu.asu.msse.pkondudu.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;   //  0 = yellow, 1 = red

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};  //  2 = unplayed

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            for (int[] winningPosition: winningPositions) {
                System.out.println("for - " + gameState[winningPosition[0]] + " " + gameState[winningPosition[1]] + " " + gameState[winningPosition[2]]);
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        gameState[winningPosition[0]] != 2) {
                    //  Someone has won
                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerMessage);
                    winnerTextView.setText(winner + " has won!");
                    linearLayout.setVisibility(View.VISIBLE);
                    gameIsActive = false;
                } else {
                    boolean gameIsOver = true;
                    for (int counterState: gameState) {
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if (gameIsOver) {
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerMessage);
                        winnerTextView.setText("Drawn game!");
                        linearLayout.setVisibility(View.VISIBLE);
                        gameIsActive = false;
                    }
                }
            }

        }

    }

    public void playAgain(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        gameIsActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
