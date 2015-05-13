package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class VitalSignsEdit extends ActionBarActivity {

    private VitalSigns vitalSigns;
    private EditText dateET;
    private EditText heartRateET;
    private EditText breathET;
    private EditText bloodPressureET;
    private EditText temperatureET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_edit);
        Intent i = getIntent();
        vitalSigns = (VitalSigns) i.getSerializableExtra("vitalSigns");
        dateET = (EditText) findViewById(R.id.vitalSignsEditDateEditText);
        dateET.setText(vitalSigns.getDate());
        heartRateET = (EditText) findViewById(R.id.vitalSignsEditHREditText);
        heartRateET.setText(vitalSigns.getHeartRate());
        breathET = (EditText) findViewById(R.id.vitalSignsEditBreathEditText);
        breathET.setText(vitalSigns.getBreath());
        bloodPressureET = (EditText) findViewById(R.id.vitalSignsEditBPEditText);
        bloodPressureET.setText(vitalSigns.getBloodPressure());
        temperatureET = (EditText) findViewById(R.id.vitalSignsEditTemperatureEditText);
        temperatureET.setText(vitalSigns.getTemperature());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vital_signs_edit, menu);
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
        vitalSigns.setDate(String.valueOf(dateET.getText()));
        vitalSigns.setHeartRate(String.valueOf(heartRateET.getText()));
        vitalSigns.setBreath(String.valueOf(breathET.getText()));
        vitalSigns.setBloodPressure(String.valueOf(bloodPressureET.getText()));
        vitalSigns.setTemperature(String.valueOf(temperatureET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("vitalSigns", vitalSigns);
        setResult(2, goingBack);
        finish();
    }
}
