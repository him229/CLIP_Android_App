package project3310.financemenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class liabilities extends Activity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private float sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liabilities);
        lvItems = (ListView) findViewById(R.id.lvItems4);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {

        EditText etNextItem2 = (EditText) findViewById(R.id.editText4);
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem4);


        String itemText2 = etNextItem2.getText().toString();
        String itemText = etNewItem.getText().toString();

        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String date = dateFormat.format(new Date());
        itemsAdapter.add(date + ":   " + itemText2 + " " + "$" + itemText);

        etNextItem2.setText("");
        etNewItem.setText("");

        writeItems();
    }

    /* public void sendMessage(View v){
        sum = (float) 0.00;

        EditText editMessage=(EditText)findViewById(R.id.edit_message);
        TextView textView = (TextView) findViewById(R.id.textView3);

        //get text from edittext and convert it to string
        String messageString = "Sum: $" + sum;

        //set string from edittext to textview
        textView.setText(messageString);

        //clear edittext after sending text to message
       // editMessage.setText("");


    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liabilities, menu);
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

    private void readItems() {
        int i = 0;
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "liabilities.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }

      /*  if ( items.size() == 0 ) {
            sum = (float) 0.00;
            System.out.println("Sum: $" + sum);
        }
        for ( i = 0; i < items.size(); i++ )
            System.out.println(items.get(i));*/
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "liabilities.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
