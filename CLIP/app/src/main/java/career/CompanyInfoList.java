package career;


import android.content.Intent;
import android.database.Cursor;
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


public class CompanyInfoList extends ActionBarActivity {
    public ArrayList cList = new ArrayList<String>();
    private ListView lv4;
    ArrayAdapter<String> arrayAdapter4;
    DatabaseHelper myDb;
    Cursor resJB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info_list);

        lv4 = (ListView)findViewById(R.id.listView4);
        myDb = new DatabaseHelper(this);
        resJB = myDb.getDataCI();

        cList.clear();
        StringBuffer buffer = new StringBuffer();
        while (resJB.moveToNext())
        {
            buffer.setLength(0);
            buffer.append("Company Name : "+ resJB.getString(1)+"\n");
            buffer.append("Product Line : "+ resJB.getString(2)+"\n");
            buffer.append("Contact Details : "+ resJB.getString(3)+"\n");
            buffer.append("Key Facts : "+ resJB.getString(4)+"\n");
            buffer.append("Reason considering : "+ resJB.getString(5)+"\n");
            buffer.append("Resume Date : "+ resJB.getString(6)+"\n");
            buffer.append("Interview status : "+ resJB.getString(7)+"\n");
            cList.add(buffer.toString());
        }

        arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cList);
        lv4.setAdapter(arrayAdapter4);


        lv4.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                String x = arrayAdapter4.getItem(pos);
                String [] s = x.split("\n");
                String userID = s[0];
                String [] uuid = userID.split(": ");
                String userids = uuid[1];
                if(myDb.deleteCI(userids)){
                    finish();
                    startActivity(getIntent());
                    return true;
                }else{
                    return false;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_company_info_list, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                myDb = new DatabaseHelper(this);
                resJB = myDb.getDataCI();

                cList.clear();
                StringBuffer buffer = new StringBuffer();
                while (resJB.moveToNext())
                {
                    buffer.setLength(0);
                    buffer.append("Company Name : "+ resJB.getString(1)+"\n");
                    buffer.append("Product Line : "+ resJB.getString(2)+"\n");
                    buffer.append("Contact Details : "+ resJB.getString(3)+"\n");
                    buffer.append("Key Facts : "+ resJB.getString(4)+"\n");
                    buffer.append("Reason considering : "+ resJB.getString(5)+"\n");
                    buffer.append("Resume Date : "+ resJB.getString(6)+"\n");
                    buffer.append("Interview status : "+ resJB.getString(7)+"\n");
                    cList.add(buffer.toString());
                }
                arrayAdapter4.notifyDataSetChanged();
            }
        }
    }
    public void addCompanyInfo(View view){
        Intent intent = new Intent(this, CompanyInformation.class);
        startActivityForResult(intent, 1);
    }
}
