package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class AllergenEdit extends ActionBarActivity {

    private Allergen allergen;
    private EditText nameET;
    private EditText descriptionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergen_edit);
        Intent i = getIntent();
        allergen = (Allergen) i.getSerializableExtra("allergen");
        nameET = (EditText) findViewById(R.id.editAllegenNameEditText);
        nameET.setText(allergen.getName());
        descriptionET = (EditText) findViewById(R.id.editAllegenDescriptionEditText);
        descriptionET.setText(allergen.getDescription());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_allergen_edit, menu);
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
        allergen.setName(String.valueOf(nameET.getText()));
        allergen.setDescription(String.valueOf(descriptionET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("allergen", allergen);
        setResult(2, goingBack);
        finish();
    }
}
