package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import project3310.financemenu.R;


public class Education_Main extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education__main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_education__main, menu);
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

    public void goCurrentEducation(View view) {
        Intent i = new Intent(this, Current_Education.class);
        startActivity(i);
    }

    public void goFinancialAid(View view) {
        Intent i = new Intent(this, Financial_Aid.class);
        startActivity(i);
    }

    public void goGraduationPlans(View view) {
        Intent i = new Intent(this, Graduate_Plans.class);
        startActivity(i);
    }

    public void goSchoolApplied(View view) {
        Intent i = new Intent(this, School_Applied.class);
        startActivity(i);
    }

    public void goSchoolConsidered(View view) {
        Intent i = new Intent(this, School_Considered.class);
        startActivity(i);
    }
}
