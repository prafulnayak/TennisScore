package org.sairaa.tennisscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


    }

    @Override
    public void onClick(View view) {

    }
}
