package health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import project3310.financemenu.R;


public class ExercisePlanEdit extends ActionBarActivity {

    private ExercisePlan exercisePlan;
    private EditText nameET;
    private EditText descriptionET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan_edit);
        Intent i = getIntent();
        exercisePlan = (ExercisePlan) i.getSerializableExtra("exercisePlan");
        nameET = (EditText) findViewById(R.id.editExercisePlanNameEditText);
        nameET.setText(exercisePlan.getName());
        descriptionET = (EditText) findViewById(R.id.editExercisePlanDescriptionEditText);
        descriptionET.setText(exercisePlan.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_plan_edit, menu);
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
        exercisePlan.setName(String.valueOf(nameET.getText()));
        exercisePlan.setDescription(String.valueOf(descriptionET.getText()));
        Intent goingBack = new Intent();
        goingBack.putExtra("exercisePlan", exercisePlan);
        setResult(2, goingBack);
        finish();
    }
}
