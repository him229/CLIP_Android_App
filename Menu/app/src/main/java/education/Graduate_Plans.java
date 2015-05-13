package education;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.EducationDataSource;
import project3310.financemenu.R;

public class Graduate_Plans extends Activity {

    private EducationDataSource dataSource;
    private ListView lv;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduate__plans);
        dataSource = new EducationDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.listView_graduation_plans);
        items = new ArrayList<String>();
        items.add(dataSource.getGraduatePlans());

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(Graduate_Plans.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure you want to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.setGraduatePlans("none");
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
        getMenuInflater().inflate(R.menu.menu_graduate__plans, menu);
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

    public void goAddGraduatePlans(View view) {
        Intent go = new Intent(this, Add_Graduate_Plans.class);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            String school = data.getStringExtra("school");
            String work = data.getStringExtra("work");
            String living = data.getStringExtra("living");

            String text = "School: "+school+"\n"+
                    "Work: "+work+"\n"+
                    "Living: "+living+"\n";

            dataSource.setGraduatePlans(text);
            items.clear();
            items.add(text);
            itemsAdapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
