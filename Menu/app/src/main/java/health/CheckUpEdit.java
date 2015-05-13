package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class CheckUpEdit extends ActionBarActivity {

    private CheckUp checkUp;
    private EditText dateET;
    private EditText cholesterolET;
    private EditText weightET;
    private EditText bpET;
    private EditText testET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up_edit);
        Intent i = getIntent();

        checkUp = (CheckUp) i.getSerializableExtra("checkUp");

        dateET = (EditText) findViewById(R.id.checkUpEditDateEditText);
        cholesterolET = (EditText) findViewById(R.id.checkUpEditCholesterolEditText);
        weightET = (EditText) findViewById(R.id.checkUpEditWeightEditText);
        bpET = (EditText) findViewById(R.id.checkUpEditBPEditText);
        testET = (EditText) findViewById(R.id.checkUpEditTestsEditText);

        dateET.setText(checkUp.getDate());
        cholesterolET.setText(checkUp.getCholesterol());
        weightET.setText(checkUp.getWeight());
        bpET.setText(checkUp.getBloodPressure());
        testET.setText(checkUp.getTests());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_up_edit, menu);
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

        checkUp.setDate(String.valueOf(dateET.getText()));
        checkUp.setCholesterol(String.valueOf(cholesterolET.getText()));
        checkUp.setBloodPressure(String.valueOf(bpET.getText()));
        checkUp.setWeight(String.valueOf(weightET.getText()));
        checkUp.setTests(String.valueOf(testET.getText()));

        Intent goingBack = new Intent();
        goingBack.putExtra("checkUp", checkUp);
        setResult(2, goingBack);
        finish();
    }
}
