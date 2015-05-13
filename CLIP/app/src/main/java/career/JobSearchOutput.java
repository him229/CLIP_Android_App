package career;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import project3310.financemenu.R;


public class JobSearchOutput extends Activity {

    EditText JS_name;
    EditText JS_month;
    EditText JS_status;
    ArrayList<String> impArray2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_output);

        JS_name = (EditText) findViewById(R.id.editText24);
        JS_month = (EditText) findViewById(R.id.editText25);
        JS_status = (EditText) findViewById(R.id.editText26);

        Intent j = getIntent();

        impArray2 = j.getStringArrayListExtra("JS_list");

        JS_name.setText(impArray2.get(0));
        JS_month.setText(impArray2.get(1));
        JS_status.setText(impArray2.get(2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_job_search_output, menu);
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
    public void saveJobSearchOutput(View view){
        finish();
    }
}
