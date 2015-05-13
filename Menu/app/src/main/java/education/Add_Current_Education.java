package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;

public class Add_Current_Education extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__current__education);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__current__education, menu);
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

    public void submit(View view) {

        EditText schoolET = (EditText) findViewById(R.id.current_school);
        EditText degreeET = (EditText) findViewById(R.id.degree_type);
        EditText majorET = (EditText) findViewById(R.id.major);
        EditText dateET = (EditText) findViewById(R.id.graduation_date);


        String school = String.valueOf(schoolET.getText());
        String degree = String.valueOf(degreeET.getText());
        String major = String.valueOf(majorET.getText());
        String date = String.valueOf(dateET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("school", school);
        goingBack.putExtra("degree", degree);
        goingBack.putExtra("major", major);
        goingBack.putExtra("date", date);
        setResult(1, goingBack);
        finish();
    }
}
