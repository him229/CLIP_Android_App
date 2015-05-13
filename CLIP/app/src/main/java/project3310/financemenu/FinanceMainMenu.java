package project3310.financemenu;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class FinanceMainMenu extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_main_menu);
        addListenerOnButton();
    }


    Button button;
    Button CCbutton;
    Button Abutton;
    Button Lbutton;
    Button Sbutton;
    Button Gbutton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_main_menu, menu);
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

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.button3);
        CCbutton = (Button) findViewById(R.id.button4);
        Abutton = (Button) findViewById(R.id.button2);
        Lbutton = (Button) findViewById(R.id.button6);
        Sbutton = (Button) findViewById(R.id.button5);
        Gbutton = (Button) findViewById(R.id.button7);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, tempCash.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);


            }
        });

       CCbutton.setOnClickListener(new OnClickListener() {

           public void onClick(View arg0) {

               Intent intent = new Intent(context, finance_creditcards.class);
               startActivity(intent);
               overridePendingTransition(R.anim.enter, R.anim.exit);
           }
       });

        Abutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, assets.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


        Lbutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, liabilities.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }

    });

        Sbutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, Stocks.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }

        });

        Gbutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, ViewTask.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }

        });

   }
}

