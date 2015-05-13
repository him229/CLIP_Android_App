package project3310.financemenu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import education.Education_Main;
import health.Health;
import career.career;
import career.AboutMe;


public class CLIPMainMenu extends ActionBarActivity {

    Button careerButton;
    Button financeButton;
    Button healthButton;
    Button eduButton;
    Button aboutButton;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipmain_menu);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        careerButton = (Button) findViewById(R.id.career);
        financeButton = (Button) findViewById(R.id.finance);
        healthButton = (Button) findViewById(R.id.health);
        eduButton = (Button) findViewById(R.id.education);
        aboutButton = (Button) findViewById(R.id.about);
        exitButton = (Button) findViewById(R.id.leave);

        careerButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {

               Intent intent = new Intent(context, career.class);
               startActivity(intent);
               overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        financeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, FinanceMainMenu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        healthButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, Health.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        eduButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, Education_Main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(context, AboutMe.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clipmain_menu, menu);
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
