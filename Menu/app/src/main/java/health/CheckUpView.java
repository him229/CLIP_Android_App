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

import project3310.financemenu.R;
import Database.CheckUpDataSource;

public class CheckUpView extends Activity {

    private CheckUpDataSource dataSource;
    private int position;
    private List<CheckUp> items;
    private ArrayAdapter<CheckUp> itemsAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up);

        dataSource = new CheckUpDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.checkUpListView);
        items = dataSource.getAllCheckUps();

        itemsAdapter = new ArrayAdapter<CheckUp>(this,android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        editCheckUp(items.get(pos));
                    }

                }
        );
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(CheckUpView.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteCheckUp(items.get(position).getId());
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
        getMenuInflater().inflate(R.menu.menu_check_up, menu);
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

    public void goToAddCheckUpView(View view) {

        Intent go = new Intent(this, CheckUpAdd.class);
        int Result = 1;
        startActivityForResult(go, Result);

    }

    private void editCheckUp(CheckUp checkUp){
        Intent go = new Intent(this, CheckUpEdit.class);
        go.putExtra("checkUp", checkUp);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            CheckUp checkUp = (CheckUp) data.getSerializableExtra("checkUp");
            boolean success = dataSource.updateCheckUp(checkUp);
            if(success) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == checkUp.getId()) {
                        items.set(i, checkUp);
                        itemsAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

        }
        else if(resultCode == 1){

            try {
                String dateSentBack = data.getStringExtra("date");
                String cholesterolSentBack = data.getStringExtra("cholesterol");
                String bpSentBack = data.getStringExtra("bp");
                String weightSentBack = data.getStringExtra("weight");
                String testSentBack = data.getStringExtra("test");

                CheckUp checkUp = dataSource.createCheckUp(dateSentBack, cholesterolSentBack,
                        bpSentBack, weightSentBack, testSentBack);
                items.add(checkUp);
                itemsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}