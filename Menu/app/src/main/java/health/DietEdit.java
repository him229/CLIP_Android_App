package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class DietEdit extends ActionBarActivity {

    private Diet diet;
    private EditText nameET;
    private EditText descriptionET;
    private EditText startET;
    private EditText endET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit);
        Intent i = getIntent();
        diet = (Diet) i.getSerializableExtra("diet");
        nameET = (EditText) findViewById(R.id.dietEditNameEditText);
        nameET.setText(diet.getName());
        descriptionET = (EditText) findViewById(R.id.dietEditDescriptionEditText);
        descriptionET.setText(diet.getDescription());
        startET = (EditText) findViewById(R.id.dietEditStartEditText);
        startET.setText(diet.getStart());
        endET = (EditText) findViewById(R.id.dietEditEndEditText);
        endET.setText(diet.getEnd());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diet_edit, menu);
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
        diet.setName(String.valueOf(nameET.getText()));
        diet.setDescription(String.valueOf(descriptionET.getText()));
        diet.setStart(String.valueOf(startET.getText()));
        diet.setEnd(String.valueOf(endET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("diet", diet);
        setResult(2, goingBack);
        finish();
    }
}
