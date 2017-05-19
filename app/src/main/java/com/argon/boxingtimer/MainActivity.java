package com.argon.boxingtimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static int rounds=0;
    public static int minutes=0;

    public Button but1;

    public Button dernds;
    public TextView roundstxt;
    public Button incrnds;

    public Button decmin;
    public TextView minutestxt;
    public Button incmin;

    public void init()
    {
        but1=(Button)findViewById(R.id.start);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,mainscreen.class);

                startActivity(intent);

            }
        });

        dernds = (Button)findViewById(R.id.minusround);
        dernds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                roundstxt = (TextView)findViewById(R.id.textView);
                if(rounds>0)
                {
                    rounds--;
                    roundstxt.setText(""+rounds);
                }


            }
        });

        incrnds = (Button)findViewById(R.id.plusround);
        incrnds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                roundstxt = (TextView)findViewById(R.id.textView);
                if(rounds<15)
                {
                    rounds++;
                    roundstxt.setText(""+rounds);
                }


            }
        });

        decmin = (Button)findViewById(R.id.minusminute);
        decmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                minutestxt = (TextView)findViewById(R.id.textView4);
                if(minutes>0)
                {
                    minutes--;
                    minutestxt.setText(""+minutes);
                }


            }
        });

        incmin = (Button)findViewById(R.id.plusminute);
        incmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                minutestxt = (TextView)findViewById(R.id.textView4);
                if(minutes<15)
                {
                    minutes++;
                    minutestxt.setText(""+minutes);
                }


            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        init();
    }


}
