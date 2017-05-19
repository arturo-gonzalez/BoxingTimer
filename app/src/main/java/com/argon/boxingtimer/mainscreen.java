package com.argon.boxingtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class mainscreen extends AppCompatActivity {

    public int rounds = MainActivity.rounds;
    public int minutes=MainActivity.minutes;


    public Button startPause;
    public TextView roundstxt;
    public TextView mintxt;
    public Button start;
    CountDownTimer timer;
    private int i = 0;
    long millileft;

    void init()
    {
        roundstxt = (TextView)findViewById(R.id.textView7);
        mintxt = (TextView)findViewById(R.id.textView6);

        roundstxt.setText("Get Ready");
        clock3();




        startPause=(Button)findViewById(R.id.pause);
        startPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(startPause.getText().equals("Start")){
                    Log.i("Started", startPause.getText().toString());
                    startPause.setText("Pause");
                    clock(60*1000);
                } else if (startPause.getText().equals("Pause")){
                    //Log.i("Paused", startPause.getText().toString());
                    startPause.setText("Resume");
                    timerPause();
                } else if (startPause.getText().equals("Resume")){
                    timerResume();
                    startPause.setText("Pause");

                }

            }
        });

    }

    void clock(long milliseconds)
    {
        //int milliseconds = minutes*60*1000;

        timer = new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                mintxt.setText(hms);
            }

            public void onFinish(){
                if(mintxt.getText()=="00:00:00")
                {
                    MediaPlayer bell= MediaPlayer.create(mainscreen.this,R.raw.boxingbell);
                    bell.start();
                    if(i<rounds)
                    {
                        roundstxt.setText("Break");
                        clock2();
                    }
                    mintxt.setText("00:00:00");
                }

            }
        }.start();
        i++;
    }


    void clock2()
    {
        int milliseconds = 60*1000;

        timer = new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                millileft = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                mintxt.setText(hms);
            }

            public void onFinish(){
                MediaPlayer bell= MediaPlayer.create(mainscreen.this,R.raw.boxingbell);
                bell.start();
                roundstxt.setText("Round " + (rounds-(rounds-(i+1))));
                clock(60*1000);
            }

        }.start();
    }

    void clock3()
    {
        int milliseconds = 5*1000;

        timer = new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                mintxt.setText(hms);
            }

            public void onFinish(){
                roundstxt.setText("Round 1");
                MediaPlayer bell= MediaPlayer.create(mainscreen.this,R.raw.boxingbell);
                bell.start();
                clock(60*1000);
            }

        }.start();
    }

    public void timerPause() {
        timer.cancel();
    }

    private void timerResume() {
        //Log.i("min", Long.toString(min));
        //Log.i("Sec", Long.toString(sec));
        clock(millileft);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        init();
    }
}
