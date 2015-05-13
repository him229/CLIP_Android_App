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
import android.widget.ListView;

import java.util.ArrayList;

import project3310.financemenu.R;


public class ElectronicIDList extends Activity {

    public ArrayList eList = new ArrayList<String>();
    private ListView lv5;
    ArrayAdapter<String> arrayAdapter5;
    DatabaseHelper myDb;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_idlist);

        lv5 = (ListView)findViewById(R.id.listView5);
        myDb = new DatabaseHelper(this);
        res = myDb.getDataEID();
        eList.clear();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.setLength(0);
            buffer.append("Website : "+ res.getString(1)+"\n");
            buffer.append("User ID : "+ res.getString(2)+"\n");
            buffer.append("Password : "+ res.getString(3)+"\n");
            buffer.append("Security Question : "+ res.getString(4)+"\n");
            buffer.append("Answer : "+ res.getString(5)+"\n");
            eList.add(buffer.toString());
        }

        arrayAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eList);
        lv5.setAdapter(arrayAdapter5);

        lv5.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                String x = arrayAdapter5.getItem(pos);
                String [] s = x.split("\n");
                String userID = s[1];
                String [] uuid = userID.split(": ");
                String userids = uuid[1];
                if(myDb.deleteEID(userids)){
                    finish();
                    startActivity(getIntent());
                    return true;
                }else{
                    return false;
                }

            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                myDb = new DatabaseHelper(this);
                res = myDb.getDataEID();

                eList.clear();
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.setLength(0);
                    buffer.append("Website : "+ res.getString(1)+"\n");
                    buffer.append("User ID : "+ res.getString(2)+"\n");
                    buffer.append("Password : "+ res.getString(3)+"\n");
                    buffer.append("Security Question : "+ res.getString(4)+"\n");
                    buffer.append("Answer : "+ res.getString(5)+"\n");
                    eList.add(buffer.toString());
                }
                arrayAdapter5.notifyDataSetChanged();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_electronic_idlist, menu);
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
    public void addElectronicID(View view){
        Intent intent = new Intent(this, ElectronicIDDetails.class);
        startActivityForResult(intent, 1);
    }
}
