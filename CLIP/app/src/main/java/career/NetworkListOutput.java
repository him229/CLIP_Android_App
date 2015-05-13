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


public class NetworkListOutput extends Activity {

    EditText NL_name;
    EditText NL_company;
    EditText NL_date;
    EditText NL_comments;
    int i=0;
    ArrayList<String>impArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list_output);


        NL_name = (EditText) findViewById(R.id.editText15);
        NL_company = (EditText) findViewById(R.id.editText16);
        NL_date = (EditText) findViewById(R.id.editText17);
        NL_comments = (EditText) findViewById(R.id.editText18);

        Intent j = getIntent();

        impArray = j.getStringArrayListExtra("NL_list");

        NL_name.setText(impArray.get(0));
        NL_company.setText(impArray.get(1));
        NL_date.setText(impArray.get(2));
        NL_comments.setText(impArray.get(3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network_list_output, menu);
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

    public void saveNLlistOutput(View view){
        finish();
    }
}
