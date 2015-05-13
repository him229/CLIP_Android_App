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

import project3310.financemenu.R;


public class ElectronicIDDetails extends Activity {

    DatabaseHelper myDb;
    EditText editWeb, editUserID, editPassword, editSecQues, editAnswer;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_iddetails);
        myDb = new DatabaseHelper(this);
        editWeb = (EditText)findViewById(R.id.editText19);
        editUserID = (EditText)findViewById(R.id.editText20);
        editPassword = (EditText)findViewById(R.id.editText21);
        editSecQues = (EditText)findViewById(R.id.editText22);
        editAnswer = (EditText)findViewById(R.id.editText23);
        btnSave = (Button)findViewById(R.id.button18);
        AddData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_electronic_iddetails, menu);
        return true;
    }

    public void AddData(){

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataEID(editWeb.getText().toString(),
                                editUserID.getText().toString(), editPassword.getText().toString(),
                                editSecQues.getText().toString(), editAnswer.getText().toString());
                        if (isInserted == true) {
                            Toast.makeText(ElectronicIDDetails.this, "Data Added", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(ElectronicIDDetails.this, "Data NOT Added", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                }
        );
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
}
