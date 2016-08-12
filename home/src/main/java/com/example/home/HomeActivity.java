package com.example.home;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
public class HomeActivity extends AppCompatActivity {
    int lbt[]={R.drawable.lbt1,R.drawable.lbt2,R.drawable.lbt3};
    int intx;
    MyRunnable myRunnable=new MyRunnable();
    Handler handler=new Handler();
    ImageView lb,dd,d1,d2;
class  MyRunnable implements Runnable{

    @Override
    public void run() {
        intx++;
        intx=intx%3;
        lb.setImageResource(lbt[intx]);
        if (lbt[intx] == R.drawable.lbt1) {
            dd.setImageResource(R.drawable.green_point);
            d1.setImageResource(R.drawable.hen_point);
            d2.setImageResource(R.drawable.hen_point);
        } else if (lbt[intx] == R.drawable.lbt2) {
            dd.setImageResource(R.drawable.hen_point);
            d1.setImageResource(R.drawable.green_point);
            d2.setImageResource(R.drawable.hen_point);
        } else if (lbt[intx] == R.drawable.lbt3) {
            dd.setImageResource(R.drawable.hen_point);
            d1.setImageResource(R.drawable.hen_point);
            d2.setImageResource(R.drawable.green_point);
        }
        handler.postDelayed(myRunnable,5000);
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lb=(ImageView) findViewById(R.id.home_lbt);
        dd=(ImageView) findViewById(R.id.home_dd);
        d1=(ImageView) findViewById(R.id.home_d1);
        d2=(ImageView) findViewById(R.id.home_d2);
        handler.postDelayed(myRunnable,1000);
    }
}
