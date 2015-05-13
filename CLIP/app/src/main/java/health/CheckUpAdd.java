package health;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class CheckUpAdd extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up_add);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_up_add, menu);
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

        EditText dateET = (EditText) findViewById(R.id.addCheckUpDateEditText);
        EditText cholesterolET = (EditText) findViewById(R.id.addCheckUpCholesterolEditText);
        EditText bpET = (EditText) findViewById(R.id.addCheckUpBpEditText);
        EditText weightET = (EditText) findViewById(R.id.addCheckUpWeightEditText);
        EditText testET = (EditText) findViewById(R.id.addCheckUpTestEditText);

        String date = String.valueOf(dateET.getText());
        String chol = String.valueOf(cholesterolET.getText());
        String bp = String.valueOf(bpET.getText());
        String weight = String.valueOf(weightET.getText());
        String test = String.valueOf(testET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("date", date);
        goingBack.putExtra("cholesterol", chol);
        goingBack.putExtra("bp", bp);
        goingBack.putExtra("weight", weight);
        goingBack.putExtra("test", test);
        setResult(1, goingBack);
        finish();
    }
}