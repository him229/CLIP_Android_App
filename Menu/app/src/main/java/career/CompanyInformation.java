package career;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project3310.financemenu.R;


public class CompanyInformation extends ActionBarActivity {

    EditText CIname;
    EditText CIproduct;
    EditText CIdetails;
    EditText CIfacts;
    EditText CIreason;
    EditText CIdate;
    EditText CIinfo;

    DatabaseHelper myDb;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);

        myDb = new DatabaseHelper(this);
        CIname = (EditText) findViewById(R.id.editText4);
        CIproduct = (EditText) findViewById(R.id.editText5);
        CIdetails = (EditText) findViewById(R.id.editText6);
        CIfacts = (EditText) findViewById(R.id.editText7);
        CIreason = (EditText) findViewById(R.id.editText8);
        CIdate = (EditText) findViewById(R.id.editText9);
        CIinfo = (EditText) findViewById(R.id.editText10);
        btnSave = (Button) findViewById(R.id.button10);
        AddData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_company_information, menu);
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
                        boolean isInserted = myDb.insertDataCI(CIname.getText().toString(),
                                CIproduct.getText().toString(),CIdetails.getText().toString(),CIfacts.getText().toString(),
                                CIreason.getText().toString(),CIdate.getText().toString(),CIinfo.getText().toString());
                        if (isInserted==true){
                            Toast.makeText(CompanyInformation.this, "Data Added", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(CompanyInformation.this, "Data NOT Added", Toast.LENGTH_LONG).show();

                        Intent intent=new Intent();
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }
        );
    }
}
