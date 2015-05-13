package career;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import project3310.financemenu.R;


public class JobSearchList extends Activity {
    public ArrayList jList = new ArrayList<String>();
    private ListView lv3;
    ArrayAdapter<String> arrayAdapter3;
    DatabaseHelper myDb;
    Cursor resJB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_list);

        lv3 = (ListView)findViewById(R.id.listView3);
        myDb = new DatabaseHelper(this);
        resJB = myDb.getDataJS();



        jList.clear();
        StringBuffer buffer = new StringBuffer();
        while (resJB.moveToNext())
        {
            buffer.setLength(0);
            buffer.append("Company Name : "+ resJB.getString(1)+"\n");
            buffer.append("Applied Month, Year : "+ resJB.getString(2)+"\n");
            buffer.append("Current Status : "+ resJB.getString(3)+"\n");
            jList.add(buffer.toString());
        }

        arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jList);
        lv3.setAdapter(arrayAdapter3);


        lv3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                String x = arrayAdapter3.getItem(pos);
                String[] s = x.split("\n");
                String userID = s[0];
                String[] uuid = userID.split(": ");
                String userids = uuid[1];
                if (myDb.deleteJS(userids)) {
                    finish();
                    startActivity(getIntent());
                    return true;
                } else {
                    return false;
                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                myDb = new DatabaseHelper(this);
                resJB = myDb.getDataJS();

                jList.clear();
                StringBuffer buffer = new StringBuffer();
                while (resJB.moveToNext())
                {
                    buffer.setLength(0);
                    buffer.append("Company Name : "+ resJB.getString(1)+"\n");
                    buffer.append("Applied Month, Year : "+ resJB.getString(2)+"\n");
                    buffer.append("Current Status : "+ resJB.getString(3)+"\n");
                    jList.add(buffer.toString());
                }
                arrayAdapter3.notifyDataSetChanged();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_job_search_list, menu);
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
    public void addJobSearch(View view){
        Intent intent = new Intent(this, JobSearch.class);
        startActivityForResult(intent, 1);    }
}
