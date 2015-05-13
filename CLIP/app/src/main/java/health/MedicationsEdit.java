package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class MedicationsEdit extends ActionBarActivity {

    private Medications medications;
    private EditText nameET;
    private EditText dosageET;
    private EditText startET;
    private EditText endET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications_edit);
        Intent i = getIntent();
        medications = (Medications) i.getSerializableExtra("medications");
        nameET = (EditText) findViewById(R.id.medicationsEditNameEditText);
        nameET.setText(medications.getName());
        dosageET = (EditText) findViewById(R.id.medicationsEditDosageEditText);
        dosageET.setText(medications.getDosage());
        startET = (EditText) findViewById(R.id.medicationsEditStartEditText);
        startET.setText(medications.getStart());
        endET = (EditText) findViewById(R.id.medicationsEditEndEditText);
        endET.setText(medications.getEnd());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medications_edit, menu);
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
        medications.setName(String.valueOf(nameET.getText()));
        medications.setDosage(String.valueOf(dosageET.getText()));
        medications.setStart(String.valueOf(startET.getText()));
        medications.setEnd(String.valueOf(endET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("medications", medications);
        setResult(2, goingBack);
        finish();
    }
}
