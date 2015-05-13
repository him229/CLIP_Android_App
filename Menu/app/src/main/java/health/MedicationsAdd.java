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


public class MedicationsAdd extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications_add);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medications_add, menu);
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

        EditText nameET = (EditText) findViewById(R.id.addMedicationsNameEditText);
        EditText dosageET = (EditText) findViewById(R.id.addMedicationsdosageEditText);
        EditText startET = (EditText) findViewById(R.id.addMedicationsStartEditText);
        EditText endET = (EditText) findViewById(R.id.addMedicationsEndEditText);

        String name = String.valueOf(nameET.getText());
        String dosage = String.valueOf(dosageET.getText());
        String start = String.valueOf(startET.getText());
        String end = String.valueOf(endET.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("name", name);
        goingBack.putExtra("dosage", dosage);
        goingBack.putExtra("start", start);
        goingBack.putExtra("end", end);

        setResult(1, goingBack);

        finish();
    }
}


