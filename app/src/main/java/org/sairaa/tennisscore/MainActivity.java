package org.sairaa.tennisscore;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView p1point,p1game,p1set,p1set1,p1set2,p1set3;
    private TextView p2point,p2game,p2set,p2set1,p2set2,p2set3;
    private TextView player1Text, player2Text;
    private Button p1plusButton,p2plusButton,matchstart,teaBreak;
    private int pointP1,pointP2;
    private Player player1, player2;
    private final float TOTAL_SET = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        p1point = findViewById(R.id.player1_point);
        p1game = findViewById(R.id.player1_game);
        p1set = findViewById(R.id.player1_set);
        p1set1 = findViewById(R.id.player1_set1);
        p1set2 = findViewById(R.id.player1_set2);
        p1set3 = findViewById(R.id.player1_set3);

        p2point = findViewById(R.id.player2_point);
        p2game = findViewById(R.id.player2_game);
        p2set = findViewById(R.id.player2_set);
        p2set1 = findViewById(R.id.player2_set1);
        p2set2 = findViewById(R.id.player2_set2);
        p2set3 = findViewById(R.id.player2_set3);

        player1Text = findViewById(R.id.player1_name);
        player2Text = findViewById(R.id.player2_name);

        p1plusButton = findViewById(R.id.player1ButtonScorePlus);
        p1plusButton.setOnClickListener(this);

        p2plusButton = findViewById(R.id.player2ButtonScorePlus);
        p2plusButton.setOnClickListener(this);


        matchstart = findViewById(R.id.match_start);
        matchstart.setOnClickListener(this);
        teaBreak = findViewById(R.id.teabreak);
        teaBreak.setOnClickListener(this);

        // get prompt.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView
                .findViewById(R.id.editText_player1);

        final EditText userInput2 = promptsView
                .findViewById(R.id.editText_player2);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.set_details,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
//                                player1.setText(userInput.getText());
//                                player2.setText(userInput2.getText());
//                                dialog.dismiss();

                            }
                        });



        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Boolean wantToCloseDialog = (userInput.getText().toString().trim().isEmpty() && userInput2.getText().toString().trim().isEmpty());
                // if EditText is empty disable closing on possitive button
                // get user input and set it to player edit text
                if (!userInput.getText().toString().trim().isEmpty() && !userInput2.getText().toString().trim().isEmpty()){
                    player1Text.setText(userInput.getText());
                    player2Text.setText(userInput2.getText());
                    player1 = new Player(userInput.getText().toString().trim(),0);
                    player2 = new Player(userInput2.getText().toString().trim(),0);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorBackground);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.player1ButtonScorePlus:
                setPointsToScoreBoard(1,0);
                break;
            case R.id.player2ButtonScorePlus:
                setPointsToScoreBoard(0,1);
        }

    }

    private void setPointsToScoreBoard(int p1, int p2) {
        player1.setPoint(player1.getPoint()+p1);
        player2.setPoint(player2.getPoint()+p2);

        //tie break
        if(player1.getGame() == 6 & player2.getGame() == 6){
            if(player1.getPoint() >= 7 && (player1.getPoint() - player2.getPoint()) >= 2){
//                Toast.makeText(MainActivity.this,"player1 game",Toast.LENGTH_SHORT).show();
                //set setScore increased by 1 for player 1
                Toast.makeText(MainActivity.this,"player 1 game",Toast.LENGTH_SHORT).show();
                player1.setGame(player1.getGame()+1);
                setSetScore(1, 0);
                player1.setGame(0);
                player2.setGame(0);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            if(player2.getPoint() >= 7 && (player2.getPoint() - player1.getPoint() >= 2)){
                //set setScore increased by 1 for player 2
                Toast.makeText(MainActivity.this,"player 2 game",Toast.LENGTH_SHORT).show();
                player2.setGame(player2.getGame()+1);
                setSetScore(0, 1);
                player1.setGame(0);
                player2.setGame(0);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            showPointSerially(player1.getPoint(),player2.getPoint());
            showGameScore(player1.getGame(),player2.getGame());

        }else{
            if(player1.getPoint() > 3 && (player1.getPoint()-player2.getPoint()) >= 2){

                Toast.makeText(MainActivity.this,"Player1 own 1 point",Toast.LENGTH_SHORT).show();
                //set game point to increase by 1 for player1
                // set points to zero
                setGameScore(1,0);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            if(player2.getPoint() > 3 && (player2.getPoint()-player1.getPoint()) >= 2){

                Toast.makeText(MainActivity.this,"Player2 own 1 point",Toast.LENGTH_SHORT).show();
                //set game point to increase by 1 for player2
                // set points to zer
                setGameScore(0,1);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            if(player1.getPoint() == 3 && player2.getPoint() == 3){
                //duce
                Toast.makeText(MainActivity.this,"Deuce ",Toast.LENGTH_SHORT).show();
            }
            if(player1.getPoint() > 3 && player2.getPoint() == 3){
                //player1 advantage
                Toast.makeText(MainActivity.this,"Player1 Advantage",Toast.LENGTH_SHORT).show();

            }
            if(player1.getPoint() == 3 && player2.getPoint() > 3){
                //player2 advantage
                Toast.makeText(MainActivity.this,"Player2 Advantage",Toast.LENGTH_SHORT).show();

            }
            if(player1.getPoint() > 3 && player2.getPoint() > 3){
                //duce again
                // set each player point 3
                Toast.makeText(MainActivity.this,"Deuce Again",Toast.LENGTH_LONG).show();
                player1.setPoint(3);
                player2.setPoint(3);
            }

            showPoints(player1.getPoint(), player2.getPoint());
        }



    }

    // This method is called where there is a tie break
    private void showPointSerially(int p1, int p2) {
        p1point.setText(String.valueOf(p1));
        p2point.setText(String.valueOf(p2));

    }

    private void setGameScore(int g1, int g2) {
        player1.setGame(player1.getGame()+g1);
        player2.setGame(player2.getGame()+g2);
        if(player1.getGame() >= 6 && (player1.getGame() - player2.getGame() >= 2)){
            //set setScore increased by 1 for player 1
            Toast.makeText(MainActivity.this,"player 1 game",Toast.LENGTH_SHORT).show();
            setSetScore(1, 0);
            //set previous sets
//            setPreviousSet(player1.getGame(),player2.getGame());

            player1.setGame(0);
            player2.setGame(0);
        }
        if(player2.getGame() >= 6 && (player2.getGame() - player1.getGame() >= 2)){
            //set setScore increased by 1 for player 2
            Toast.makeText(MainActivity.this,"player 2 game",Toast.LENGTH_SHORT).show();
            setSetScore(0,1);
            //set previous sets
//            setPreviousSet(player1.getGame(),player2.getGame());
            player1.setGame(0);
            player2.setGame(0);
        }

        showGameScore(player1.getGame(),player2.getGame());

    }

    private void setPreviousSet(int p1PreviousSet, int p2previousSet) {
        if(player1.getSet1() == 0 && player2.getSet1() == 0){
            player1.setSet1(p1PreviousSet);
            player2.setSet1(p2previousSet);

            p1set1.setText(String.valueOf(player1.getSet1()));
            p2set1.setText(String.valueOf(player2.getSet1()));
        }

    }
    // this method set the set score og the match
    // as well as keeps the record of previous set score
    private void setSetScore(int s1, int s2) {
        player1.setSet(player1.getSet() + s1);
        player2.setSet(player2.getSet() + s2);
        int noOfSets = player1.getSet() + player2.getSet();
 //     Set set score board
        p1set.setText(String.valueOf(player1.getSet()));
        p2set.setText(String.valueOf(player2.getSet()));

        if(noOfSets <=TOTAL_SET) {
            switch (noOfSets) {
                case 1:
                    //set previous set1
                    player1.setSet1(player1.getGame());
                    player2.setSet1(player2.getGame());

                    p1set1.setText(String.valueOf(player1.getSet1()));
                    p2set1.setText(String.valueOf(player2.getSet1()));
                    break;
                case 2:
                    //set previous set2
                    player1.setSet2(player1.getGame());
                    player2.setSet2(player2.getGame());

                    p1set2.setText(String.valueOf(player1.getSet2()));
                    p2set2.setText(String.valueOf(player2.getSet2()));
                    break;
                case 3:
                    //set previous set3
                    player1.setSet3(player1.getGame());
                    player2.setSet3(player2.getGame());

                    p1set3.setText(String.valueOf(player1.getSet3()));
                    p2set3.setText(String.valueOf(player2.getSet3()));
                    // game over
                    //Show winner
                    //disable '+' buttons
                    break;
            }
        }
        if(player1.getSet() > TOTAL_SET/2){
            //player 1 won the match
            p1plusButton.setVisibility(Button.INVISIBLE);
            p2plusButton.setVisibility(Button.INVISIBLE);
        }else if(player2.getSet() > TOTAL_SET/2){
            //player 2 winner
            p1plusButton.setVisibility(Button.INVISIBLE);
            p2plusButton.setVisibility(Button.INVISIBLE);
        }

    }

    private void showGameScore(int g1, int g2) {
        p1game.setText(String.valueOf(g1));
        p2game.setText(String.valueOf(g2));

    }

    // Display points in score board
    private void showPoints(int p1, int p2) {

            switch (p1){
                case 0:
                    p1point.setText("00");
                    break;
                case 1:
                    p1point.setText("15");
                    break;
                case 2:
                    p1point.setText("30");
                    break;
                case 3:
                    p1point.setText("40");
                    break;
                case 4:
                    p1point.setText("Ad");
                    break;
                default:
                    p1point.setText("EE");

            }
            switch (p2){
                case 0:
                    p2point.setText("00");
                    break;
                case 1:
                    p2point.setText("15");
                    break;
                case 2:
                    p2point.setText("30");
                    break;
                case 3:
                    p2point.setText("40");
                    break;
                case 4:
                    p2point.setText("Ad");
                    break;
                default:
                    p2point.setText("EE");

            }


    }
}
