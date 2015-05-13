package health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ExercisePlanDataSource;
import project3310.financemenu.R;


public class ExercisePlanView extends Activity {

    private ExercisePlanDataSource dataSource;
    private ListView lv;
    private ArrayAdapter<ExercisePlan> itemsAdapter;
    private List<ExercisePlan> items;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);

        dataSource = new ExercisePlanDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.exercisePlanListView);
        items = dataSource.getAllExercisePlans();

        itemsAdapter = new ArrayAdapter<ExercisePlan>(this,android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        editExercisePlans(items.get(pos));
                    }

                }
        );
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(ExercisePlanView.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure you want to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteExercisePlan(items.get(position).getId());
                                items.remove(position);
                                itemsAdapter.notifyDataSetChanged();
                                dialog.dismiss();

                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                        alert.show();
                        return true;
                    }

                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_plan, menu);
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

    public void goToAddExercisePlanView(View view) {

        Intent go = new Intent(this, ExercisePlanAdd.class);
        int Result = 1;
        startActivityForResult(go, Result);

    }

    private void editExercisePlans(ExercisePlan exercisePlan){
        Intent go = new Intent(this, ExercisePlanEdit.class);
        go.putExtra("exercisePlan", exercisePlan);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            ExercisePlan exercisePlan = (ExercisePlan) data.getSerializableExtra("exercisePlan");
            boolean success = dataSource.updateExercisePlan(exercisePlan);
            if(success) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == exercisePlan.getId()) {
                        items.set(i, exercisePlan);
                        itemsAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

        }
        else if(resultCode == 1){

            try {
                String nameSentBack = data.getStringExtra("name");
                String descriptionSentBack = data.getStringExtra("description");

                ExercisePlan exercisePlan = dataSource.createExercisePlan(nameSentBack, descriptionSentBack);
                System.out.println(exercisePlan);
                items.add(exercisePlan);
                itemsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
