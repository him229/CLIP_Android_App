package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;

public class Add_Graduate_Plans extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__graduate__plans);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__graduate__plans, menu);
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

        EditText schoolET = (EditText) findViewById(R.id.school_plans);
        EditText workET = (EditText) findViewById(R.id.work_plans);
        EditText livingET = (EditText) findViewById(R.id.living_plans);


        String school = String.valueOf(schoolET.getText());
        String work = String.valueOf(workET.getText());
        String living = String.valueOf(livingET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("school", school);
        goingBack.putExtra("work", work);
        goingBack.putExtra("living", living);
        setResult(1, goingBack);
        finish();
    }
}
