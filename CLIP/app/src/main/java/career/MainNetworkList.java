package career;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import project3310.financemenu.R;


public class MainNetworkList extends Activity {
    public ArrayList nList = new ArrayList<String>();
    private ListView lv2;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList<String> NL_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_network_list);

        lv2 = (ListView)findViewById(R.id.listView2);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nList);
        lv2.setAdapter(arrayAdapter2);

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String message1 = arrayAdapter2.getItem(position).toString();
                Intent i = new Intent(MainNetworkList.this, NetworkListOutput.class);
                i.putStringArrayListExtra("NL_list",NL_list);
                startActivity(i);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                NL_list = data.getStringArrayListExtra("NLlist");
                nList.add(NL_list.get(0));
                arrayAdapter2.notifyDataSetChanged();
            }
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_network_list, menu);
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
    public void addButtonClickNetwork(View view){
        Intent intent = new Intent(this, NetworkList.class);
        startActivityForResult(intent, 1);
    }
}
