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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView p1point,p1game,p1set,p1set1,p1set2,p1set3;
    private TextView p2point,p2game,p2set,p2set1,p2set2,p2set3;
    private TextView player1, player2;
    private Button p1plusButton,p2plusButton,matchstart,teaBreak;
    private int pointP1,pointP2;
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

        player1 = findViewById(R.id.player1_name);
        player2 = findViewById(R.id.player2_name);

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
                                // get user input and set it to result
                                // edit text
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
                if (!userInput.getText().toString().trim().isEmpty() && !userInput2.getText().toString().trim().isEmpty()){
                    player1.setText(userInput.getText());
                    player2.setText(userInput2.getText());
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorBackground);
    }

    @Override
    public void onClick(View view) {

    }
}
