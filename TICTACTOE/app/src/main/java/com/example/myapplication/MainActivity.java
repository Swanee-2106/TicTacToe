package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isWinner=false;
    int imageClicked=-1;
    int player=1,t=0; //player1 is cross

    int [][]winningstates={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6},{0, 3, 6},{1, 4, 7},{2 ,4, 8}};
    int []gameState={-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[] ws={-1,-1,-1};


    public void load(View view) {

        ImageView v = (ImageView) view;

        int tag = Integer.parseInt(v.getTag().toString());
        imageClicked = gameState[tag];
        if (isWinner == false && imageClicked == -1) {
            if (player == 1) {
                v.setImageResource(R.drawable.cross);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "Cross", Toast.LENGTH_SHORT).show();
                player = 0; //here zero player 
            } else {
                v.setImageResource(R.drawable.zero);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "Zero", Toast.LENGTH_SHORT).show();
                player = 1;
            }

            for (int i = 0; i < winningstates.length; i++) {
                if (gameState[winningstates[i][0]] == gameState[winningstates[i][1]] && gameState[winningstates[i][1]] == gameState[winningstates[i][2]] && gameState[winningstates[i][0]] > -1) {
                    if(player==1)
                        Toast.makeText(this, "Winner: " + "O", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Winner: " + "X", Toast.LENGTH_SHORT).show();
                    isWinner = true;
                    if(player==1){
                        ws[t]=0;
                    }
                    else{
                        ws[t]=1;
                    }
                    t=t+1;
                }
                if(t==3){
                    t=0;
                    if(ws[0]==ws[1] || ws[0]==ws[2]){
                        if(ws[0]==1)
                            Toast.makeText(this, "Winner of Competition is: " + "X", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(this, "Winner of Competition is: " + "O", Toast.LENGTH_LONG).show();
                    }
                    else if(ws[1]==ws[2]){
                        if(ws[1]==1)
                            Toast.makeText(this, "Winner of Competition is: " + "X", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(this, "Winner of Competition is: " + "O", Toast.LENGTH_LONG).show();
                    }
                    reset(view);
                }
            }
        }
    }

    public void reset(View view){
        GridLayout gridLayout=findViewById(R.id.gridlayout);
        int total_images=gridLayout.getChildCount();
        for(int i=0;i<total_images;i++){
            ImageView v= (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner=false;
        imageClicked=-1;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=-1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
