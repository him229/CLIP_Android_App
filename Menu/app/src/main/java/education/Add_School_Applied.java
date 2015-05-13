package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;

public class Add_School_Applied extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__school__applied);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__school__applied, menu);
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

        EditText nameET = (EditText) findViewById(R.id.school_applied);
        EditText degreeET = (EditText) findViewById(R.id.degree_type);
        EditText areaET = (EditText) findViewById(R.id.area_study);
        EditText costET = (EditText) findViewById(R.id.yearly_cost);
        EditText costAppET = (EditText) findViewById(R.id.application_cost);
        EditText appDeadET = (EditText) findViewById(R.id.application_deadline);

        String name = String.valueOf(nameET.getText());
        String degree = String.valueOf(degreeET.getText());
        String area = String.valueOf(areaET.getText());
        String cost = String.valueOf(costET.getText());
        String costApp = String.valueOf(costAppET.getText());
        String appDead = String.valueOf(appDeadET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("name", name);
        goingBack.putExtra("degree", degree);
        goingBack.putExtra("area", area);
        goingBack.putExtra("cost", cost);
        goingBack.putExtra("costApp", costApp);
        goingBack.putExtra("appDead", appDead);
        setResult(1, goingBack);
        finish();
    }
}
