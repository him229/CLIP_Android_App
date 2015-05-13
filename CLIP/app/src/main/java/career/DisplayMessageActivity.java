package career;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import project3310.financemenu.R;


public class DisplayMessageActivity extends ActionBarActivity {


    public ArrayList stGoals = new ArrayList<String>();
    private ListView lv;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        lv = (ListView)findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stGoals);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message1 = arrayAdapter.getItem(position).toString();
                Intent i = new Intent(DisplayMessageActivity.this, ShortTermGoalDetails.class);
                i.putExtra("message1",message1);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String stgoal = data.getStringExtra("sGoal");
                stGoals.add(stgoal);
                arrayAdapter.notifyDataSetChanged();
            }
        }

    }

    /*TODO:
    * make temp array, copy of stGoals
    * Clear stgoals and array Adapter
    * Repopulate arrayadpter*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
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
    public void addButtonClick(View view){
        Intent intent = new Intent(this, ShortTermGoalDetails.class);
        intent.putExtra("message1","");
        startActivityForResult(intent, 1);
    }

}
