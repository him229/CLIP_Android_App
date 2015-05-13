package career;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import project3310.financemenu.R;


public class JobSearch extends Activity {
    EditText JSname;
    EditText JSmonth;
    EditText JSstatus;
    DatabaseHelper myDb;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);

        myDb = new DatabaseHelper(this);
        JSname = (EditText) findViewById(R.id.editText);
        JSmonth = (EditText) findViewById(R.id.editText2);
        JSstatus = (EditText) findViewById(R.id.editText3);
        btnSave = (Button) findViewById(R.id.button9);
        AddData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_job_search, menu);
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
    public void AddData(){

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataJS(JSname.getText().toString(),
                                JSmonth.getText().toString(),JSstatus.getText().toString());
                        if (isInserted==true){
                            Toast.makeText(JobSearch.this, "Data Added", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(JobSearch.this, "Data NOT Added", Toast.LENGTH_LONG).show();

                        Intent intent=new Intent();
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }
        );
    }

}
