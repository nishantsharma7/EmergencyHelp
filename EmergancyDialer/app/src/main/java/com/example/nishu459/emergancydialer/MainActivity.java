package com.example.nishu459.emergancydialer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    CardView card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);
        card4=(CardView)findViewById(R.id.card4);
        card5=(CardView)findViewById(R.id.card5);
        card6=(CardView)findViewById(R.id.card6);
        card7=(CardView)findViewById(R.id.card7);
        card8=(CardView)findViewById(R.id.card8);
        card9=(CardView)findViewById(R.id.card9);
        card10=(CardView)findViewById(R.id.card10);
        card11=(CardView)findViewById(R.id.card11);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                intent.putExtra("need", 1 + "");
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",2+"");
                startActivity(i);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",3+"");
                startActivity(i);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",4+"");
                startActivity(i);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",5+"");
                startActivity(i);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",6+"");
                startActivity(i);
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",7+"");
                startActivity(i);
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",8+"");
                startActivity(i);
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",9+"");
                startActivity(i);
            }
        });

        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",10+"");
                startActivity(i);
            }
        });

        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewActivity.class);
                i.putExtra("need",11+"");
                startActivity(i);
            }
        });


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
