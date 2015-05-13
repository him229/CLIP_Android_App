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


public class VitalSignsAdd extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_add);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vital_signs_add, menu);
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

        EditText dateET = (EditText) findViewById(R.id.addVitalSignsDateEditText);
        EditText HRET = (EditText) findViewById(R.id.addVitalSignsRateEditText);
        EditText breathET = (EditText) findViewById(R.id.addVitalSignsBreathEditText);
        EditText BPET = (EditText) findViewById(R.id.addVitalSignsBPEditText);
        EditText temperatureET = (EditText) findViewById(R.id.addVitalSignsTemperatureEditText);

        String date = String.valueOf(dateET.getText());
        String HR = String.valueOf(HRET.getText());
        String breath = String.valueOf(breathET.getText());
        String BP = String.valueOf(BPET.getText());
        String temperature = String.valueOf(temperatureET.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("date", date);
        goingBack.putExtra("HR", HR);
        goingBack.putExtra("breath", breath);
        goingBack.putExtra("BP", BP);
        goingBack.putExtra("temperature", temperature);

        setResult(1, goingBack);

        finish();
    }
}
