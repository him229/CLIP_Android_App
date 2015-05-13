package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class RecipesEdit extends ActionBarActivity {

    private Recipes recipes;
    private EditText nameET;
    private EditText descriptionET;
    private EditText startET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_edit);
        Intent i = getIntent();
        recipes = (Recipes) i.getSerializableExtra("recipes");
        nameET = (EditText) findViewById(R.id.recipesEditNameEditText);
        nameET.setText(recipes.getName());
        descriptionET = (EditText) findViewById(R.id.recipesEditDescriptionEditText);
        descriptionET.setText(recipes.getDescription());
        startET = (EditText) findViewById(R.id.recipesEditURLEditText);
        startET.setText(recipes.getURL());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipes_edit, menu);
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
        recipes.setName(String.valueOf(nameET.getText()));
        recipes.setDescription(String.valueOf(descriptionET.getText()));
        recipes.setURL(String.valueOf(startET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("recipes", recipes);
        setResult(2, goingBack);
        finish();
    }
}
