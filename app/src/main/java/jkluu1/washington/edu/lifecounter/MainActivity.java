package jkluu1.washington.edu.lifecounter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ArrayList<Integer> players = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players.add(-1);
        players.add(20);

        final LinearLayout myRoot = (LinearLayout) findViewById(R.id.rows);

        setPlayers(myRoot);
        players.add(20);
        setPlayers(myRoot);

        Button newPlayer = (Button) findViewById(R.id.addPlayer);
        newPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (players.size() < 9) {
                    players.add(20);
                    TextView title = (TextView) findViewById(R.id.numOfPlayers);
                    title.setText(players.size() - 1 + " Players");

                    setPlayers(myRoot);
                }
            }
        });
    }

    public void setPlayers(LinearLayout myRoot) {
        final View inflated = LayoutInflater.from(getApplicationContext()).inflate(R.layout.playercontrols, myRoot, false);
        TextView playerName = (TextView) inflated.findViewById(R.id.playerNum);
        final int playerNumber = players.size() - 1;
        playerName.setText("Player " + playerNumber + ": ");

        Button minusFive = (Button) inflated.findViewById(R.id.minusFive);
        Button minusOne = (Button) inflated.findViewById(R.id.minusOne);
        Button plusOne = (Button) inflated.findViewById(R.id.plusOne);
        Button plusFive = (Button) inflated.findViewById(R.id.plusFive);


        // create a listener
        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView score = (TextView) inflated.findViewById(R.id.score);
                TextView loser = (TextView) findViewById(R.id.loser);
                switch (v.getId()) {
                    case R.id.minusFive:
                        players.set(playerNumber, players.get(playerNumber) - 5);
                        score.setText("" + players.get(playerNumber));
                        break;
                    case R.id.minusOne:
                        players.set(playerNumber, players.get(playerNumber) - 1);
                        score.setText("" + players.get(playerNumber));
                        break;
                    case R.id.plusOne:
                        players.set(playerNumber, players.get(playerNumber) + 1);
                        score.setText("" + players.get(playerNumber));
                        break;
                    case R.id.plusFive:
                        players.set(playerNumber, players.get(playerNumber) + 5);
                        score.setText("" + players.get(playerNumber));
                        break;
                }
                if (players.get(playerNumber) <= 0) {
                    loser.setText("Player " + playerNumber + " Loses!");
                }
            }
        };

        minusFive.setOnClickListener(handler);
        minusOne.setOnClickListener(handler);
        plusFive.setOnClickListener(handler);
        plusOne.setOnClickListener(handler);

        myRoot.addView(inflated);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
