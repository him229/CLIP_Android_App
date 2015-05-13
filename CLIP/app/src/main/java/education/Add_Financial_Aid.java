package education;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;

public class Add_Financial_Aid extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__financial__aid);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__financial__aid, menu);
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

        EditText loanET = (EditText) findViewById(R.id.loan);
        EditText scholarshipET = (EditText) findViewById(R.id.scholarship);
        EditText grantsET = (EditText) findViewById(R.id.grants);


        String loan = String.valueOf(loanET.getText());
        String scholarship = String.valueOf(scholarshipET.getText());
        String grants = String.valueOf(grantsET.getText());

        Intent goingBack = new Intent();
        goingBack.putExtra("loan", loan);
        goingBack.putExtra("scholarship", scholarship);
        goingBack.putExtra("grants", grants);
        setResult(1, goingBack);
        finish();
    }
}
