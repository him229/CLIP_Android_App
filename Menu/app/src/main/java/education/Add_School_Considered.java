package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;

public class Add_School_Considered extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__school__considered);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__school__considered, menu);
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

        EditText nameET = (EditText) findViewById(R.id.school_considered);
        EditText degreeET = (EditText) findViewById(R.id.degree_type_considered);
        EditText areaET = (EditText) findViewById(R.id.area_study_considered);
        EditText costET = (EditText) findViewById(R.id.yearly_cost_considered);

        String name = String.valueOf(nameET.getText());
        String degree = String.valueOf(degreeET.getText());
        String area = String.valueOf(areaET.getText());
        String cost = String.valueOf(costET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("name", name);
        goingBack.putExtra("degree", degree);
        goingBack.putExtra("area", area);
        goingBack.putExtra("cost", cost);
        setResult(1, goingBack);
        finish();
    }
}
