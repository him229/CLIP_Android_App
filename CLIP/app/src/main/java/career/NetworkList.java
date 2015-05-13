package career;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import project3310.financemenu.R;


public class NetworkList extends Activity {

    EditText NLname;
    EditText NLcompany;
    EditText NLdate;
    EditText NLcomments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network_list, menu);
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
    public void saveButton2(View view){
        Intent intent = new Intent();

        NLname = (EditText) findViewById(R.id.editText11);
        NLcompany = (EditText) findViewById(R.id.editText12);
        NLdate = (EditText) findViewById(R.id.editText13);
        NLcomments = (EditText) findViewById(R.id.editText14);

        String name = NLname.getText().toString();
        String company  = NLcompany.getText().toString();
        String date = NLdate.getText().toString();
        String comments = NLcomments.getText().toString();

        ArrayList NLlist = new ArrayList<String>();
        NLlist.add(name);
        NLlist.add(company);
        NLlist.add(date);
        NLlist.add(comments);

        intent.putStringArrayListExtra("NLlist", NLlist);
        setResult(RESULT_OK, intent);
        finish();
    }
}
