package org.sairaa.tennisscore;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView p1point,p1game,p1set,p1set1,p1set2,p1set3;
    private TextView p2point,p2game,p2set,p2set1,p2set2,p2set3;
    private TextView player1Text, player2Text;
    private Button p1plusButton,p2plusButton,matchstart,teaBreak, player1Ball, player2Ball,player1DF,player2DF;
    private Player player1, player2;
    private final float TOTAL_SET = 3;
    boolean mTopLeft = true;
    private TextView player1Name, player2Name;

    private static final DecelerateInterpolator sDecelerateInterpolator =
            new DecelerateInterpolator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Ball = findViewById(R.id.player1_head);
        player2Ball = findViewById(R.id.player2_head);

        player1Name = findViewById(R.id.player1_name1);
        player2Name = findViewById(R.id.player2_name2);

        player1DF = findViewById(R.id.player1DoubleFault);
        player1DF.setOnClickListener(this);
        player2DF = findViewById(R.id.player2DoubleFault);
        player2DF.setOnClickListener(this);

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

        p1plusButton.setVisibility(View.INVISIBLE);
        p2plusButton.setVisibility(View.INVISIBLE);
        player1DF.setVisibility(View.INVISIBLE);
        player2DF.setVisibility(View.INVISIBLE);
        teaBreak.setVisibility(View.INVISIBLE);

        // get prompt.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        //to enter Player1 & Player2
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

                            }
                        });



        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if EditText is empty disable closing on possitive button
                // get user input and set it to player edit text
                if (!userInput.getText().toString().trim().isEmpty() && !userInput2.getText().toString().trim().isEmpty()){
                    String player1Namea = userInput.getText().toString().trim();
                    String player2Nameb = userInput2.getText().toString().trim();
                    // Set Players Name to Score board and Head button
                    player1Text.setText(player1Namea);
                    player2Text.setText(player2Nameb);
                    player1 = new Player(player1Namea,0);
                    player2 = new Player(player2Nameb,0);

                    player1Ball.setText(String.valueOf(player1Namea.charAt(0)));
                    player2Ball.setText(String .valueOf(player2Nameb.charAt(0)));
                    player1Name.setText(player1Namea);
                    player2Name.setText(player2Nameb);
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
                break;
            case R.id.player1DoubleFault:
                setPointsToScoreBoard(0,1);
                break;
            case R.id.player2DoubleFault:
                setPointsToScoreBoard(1,0);
                break;
            case R.id.match_start:
                //Start Match Button
                // active all other button
                // disable start button
                // ask for who to serve first
                matchstart.setVisibility(View.INVISIBLE);
                p1plusButton.setVisibility(Button.VISIBLE);
                p2plusButton.setVisibility(Button.VISIBLE);
                player1DF.setVisibility(View.VISIBLE);
                player2DF.setVisibility(View.VISIBLE);
                teaBreak.setVisibility(View.VISIBLE);
                break;
            case R.id.teabreak:
                //Reset Button
                player1.reset();

                player2.reset();
                setTextViewEmplt();
                matchstart.setVisibility(View.VISIBLE);
                p1plusButton.setVisibility(Button.INVISIBLE);
                p2plusButton.setVisibility(Button.INVISIBLE);
                player1DF.setVisibility(View.INVISIBLE);
                player2DF.setVisibility(View.INVISIBLE);
                teaBreak.setVisibility(View.INVISIBLE);
                break;


        }

    }

    private void setTextViewEmplt() {
        p1point.setText("");
        p1game.setText("");
        p1set.setText("");
        p1set1.setText("");
        p1set2.setText("");
        p1set3.setText("");

        p2point.setText("");
        p2game.setText("");
        p2set.setText("");
        p2set1.setText("");
        p2set2.setText("");
        p2set3.setText("");
    }

    private void moveButton(int i) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) player1Ball.getLayoutParams();
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) player2Ball.getLayoutParams();
        // if i = 0, set the service point to right side of the court
        // if i = 1, set the service point to left side of the court
        if (i==0) {
            params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            params2.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params2.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);


        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params2.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params2.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
        }
        player1Ball.setLayoutParams(params);
        mTopLeft = !mTopLeft;
    }

    private void setPointsToScoreBoard(int p1, int p2) {
        player1.setPoint(player1.getPoint()+p1);
        player2.setPoint(player2.getPoint()+p2);

        //tie break
        if(player1.getGame() == 6 & player2.getGame() == 6){
            // On tie break situation the points to be shown serially like 1,2,3,4,5,6,7...
            //insted of 15,30,40
            if(player1.getPoint() >= 7 && (player1.getPoint() - player2.getPoint()) >= 2){

                //set setScore increased by 1 for player 1
                player1.setGame(player1.getGame()+1);
                setDialogMessage(getString(R.string.set_score),player1.getName()+" "+getString(R.string.alert_msg_part)
                        +"\n"+player1.getName()+" : "+player1.getGame()+"\n"+player2.getName()+" : "+player2.getGame());

                setSetScore(1, 0);
                player1.setGame(0);
                player2.setGame(0);
                player1.setPoint(0);
                player2.setPoint(0);

            }
            if(player2.getPoint() >= 7 && (player2.getPoint() - player1.getPoint() >= 2)){
                //set setScore increased by 1 for player 2
//                Toast.makeText(MainActivity.this,"player 2 game",Toast.LENGTH_SHORT).show();
                player2.setGame(player2.getGame()+1);
                setDialogMessage(getString(R.string.set_score),player2.getName()+" "+getString(R.string.alert_msg_part)
                        +"\n"+player1.getName()+" : "+player1.getGame()+"\n"+player2.getName()+" : "+player2.getGame());
                setSetScore(0, 1);
                player1.setGame(0);
                player2.setGame(0);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            // On tie break situation the points to be shown serially like 1,2,3,4,5,6,7...
            //insted of 15,30,40
            showPointSerially(player1.getPoint(),player2.getPoint());
            //display the game score
            showGameScore(player1.getGame(),player2.getGame());

        }else{
            if(player1.getPoint() > 3 && (player1.getPoint()-player2.getPoint()) >= 2){


                //set game point to increase by 1 for player1
                // set points to zero
                setGameScore(1,0);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            if(player2.getPoint() > 3 && (player2.getPoint()-player1.getPoint()) >= 2){


                //set game point to increase by 1 for player2
                // set points to zer0
                setGameScore(0,1);
                player1.setPoint(0);
                player2.setPoint(0);
            }
            if(player1.getPoint() == 3 && player2.getPoint() == 3){
                //duce
                Toast.makeText(MainActivity.this,""+getString(R.string.deuce),Toast.LENGTH_SHORT).show();
            }
            if(player1.getPoint() > 3 && player2.getPoint() == 3){
                //player1 advantage
                Toast.makeText(MainActivity.this,""+player1.getName()+" "+getString(R.string.advantage),Toast.LENGTH_SHORT).show();

            }
            if(player1.getPoint() == 3 && player2.getPoint() > 3){
                //player2 advantage
                Toast.makeText(MainActivity.this,""+player2.getName()+" "+getString(R.string.advantage),Toast.LENGTH_SHORT).show();

            }
            if(player1.getPoint() > 3 && player2.getPoint() > 3){
                //duce again
                // set each player point 3
                Toast.makeText(MainActivity.this,getString(R.string.deuce_again),Toast.LENGTH_LONG).show();
                player1.setPoint(3);
                player2.setPoint(3);
            }

            showPoints(player1.getPoint(), player2.getPoint());
            //move player button across the court according to service
            moveButton((player1.getPoint()+player2.getPoint())%2);
        }



    }

    private void setDialogMessage(String titleT, String msgT) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.result, null);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertBuilder.setView(promptsView);

        final TextView title = promptsView
                .findViewById(R.id.title_result);
        title.setText(titleT);
        final TextView msg = promptsView
                .findViewById(R.id.message_alert);
        msg.setText(msgT);
        alertBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorBackground);


    }

    // This method is called where there is a tie break
    private void showPointSerially(int p1, int p2) {
        p1point.setText(String.valueOf(p1));
        p2point.setText(String.valueOf(p2));

    }
    // This method set the game score for each player
    private void setGameScore(int g1, int g2) {
        player1.setGame(player1.getGame()+g1);
        player2.setGame(player2.getGame()+g2);
        setDialogMessage(getString(R.string.set_score),
                "\n"+player1.getName()+" : "+player1.getGame()+"\n"+player2.getName()+" : "+player2.getGame());
        //Tie Break
        if(player1.getGame() == 6 & player2.getGame() == 6) {
            //Show Alert dialog
            setDialogMessage(getString(R.string.tie_break), getString(R.string.tie_msg));
        }
        if(player1.getGame() >= 6 && (player1.getGame() - player2.getGame() >= 2)){
            //set setScore increased by 1 for player 1
            setSetScore(1, 0);
            //set game score to zero
            player1.setGame(0);
            player2.setGame(0);
        }
        if(player2.getGame() >= 6 && (player2.getGame() - player1.getGame() >= 2)){
            //set setScore increased by 1 for player 2
            setSetScore(0,1);
            //set game score to zero
            player1.setGame(0);
            player2.setGame(0);
        }

        showGameScore(player1.getGame(),player2.getGame());

    }

    // this method set the set score og the match
    // as well as keeps the record of previous set score
    private void setSetScore(int s1, int s2) {
        player1.setSet(player1.getSet() + s1);
        player2.setSet(player2.getSet() + s2);

        setDialogMessage(getString(R.string.match_score),
                "\n"+player1.getName()+" : "+player1.getSet()+"\n"+player2.getName()+" : "+player2.getSet());

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

                    break;
            }
        }
        if(player1.getSet() > TOTAL_SET/2){
            //player 1 won the match
            p1plusButton.setVisibility(Button.INVISIBLE);
            p2plusButton.setVisibility(Button.INVISIBLE);
            player1DF.setVisibility(View.INVISIBLE);
            player2DF.setVisibility(View.INVISIBLE);
            teaBreak.setVisibility(View.VISIBLE);
            setDialogMessage(getString(R.string.winner),getString(R.string.won_match)+"\n"+player1.getName());
        }else if(player2.getSet() > TOTAL_SET/2){
            //player 2 winner
            p1plusButton.setVisibility(Button.INVISIBLE);
            p2plusButton.setVisibility(Button.INVISIBLE);
            player1DF.setVisibility(View.INVISIBLE);
            player2DF.setVisibility(View.INVISIBLE);
            teaBreak.setVisibility(View.VISIBLE);
            setDialogMessage(getString(R.string.winner),getString(R.string.won_match)+"\n"+player2.getName());
        }

    }
    // This method display the game score
    private void showGameScore(int g1, int g2) {
        p1game.setText(String.valueOf(g1));
        p2game.setText(String.valueOf(g2));

    }

    // Display points in score board
    private void showPoints(int p1, int p2) {

            switch (p1){
                case 0:
                    p1point.setText(getString(R.string.zero));
                    break;
                case 1:
                    p1point.setText(getString(R.string.fifteen));
                    break;
                case 2:
                    p1point.setText(getString(R.string.thirty));
                    break;
                case 3:
                    p1point.setText(getString(R.string.fourty));
                    break;
                case 4:
                    p1point.setText(getString(R.string.adv));
                    break;
                default:
                    p1point.setText(getString(R.string.error));

            }
            switch (p2){
                case 0:
                    p2point.setText(getString(R.string.zero));
                    break;
                case 1:
                    p2point.setText(getString(R.string.fifteen));
                    break;
                case 2:
                    p2point.setText(getString(R.string.thirty));
                    break;
                case 3:
                    p2point.setText(getString(R.string.fourty));
                    break;
                case 4:
                    p2point.setText(getString(R.string.adv));
                    break;
                default:
                    p2point.setText(getString(R.string.error));

            }


    }
}
