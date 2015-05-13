package health;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import project3310.financemenu.R;


public class Health extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health, menu);
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

    public void goToAllergenView(View view) {

        Intent goTo = new Intent(this, AllergenView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);

    }

    public void goToVitalSignsView(View view) {

        Intent goTo = new Intent(this, VitalSignsView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToRecipesView(View view) {

        Intent goTo = new Intent(this, RecipesView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToMedicationsView(View view) {

        Intent goTo = new Intent(this, MedicationsView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToHeathNoteView(View view) {

        Intent goTo = new Intent(this, HealthNotesView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToExerPlanView(View view) {

        Intent goTo = new Intent(this, ExercisePlanView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToDietView(View view) {

        Intent goTo = new Intent(this, DietView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void goToCheckUpView(View view) {

        Intent goTo = new Intent(this, CheckUpView.class);
        startActivity(goTo);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }
}
