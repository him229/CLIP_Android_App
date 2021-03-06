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
import Database.DietDataSource;

public class DietView extends Activity {

    private DietDataSource dataSource;
    private ListView lv;
    private ArrayAdapter<Diet> itemsAdapter;
    private List<Diet> items;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        dataSource = new DietDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.dietListView);
        items = dataSource.getAllDiets();

        itemsAdapter = new ArrayAdapter<Diet>(this,android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        editDiets(items.get(pos));
                    }

                }
        );
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(DietView.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure you want to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteDiet(items.get(position).getId());
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
        getMenuInflater().inflate(R.menu.menu_diet, menu);
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

    public void goToAddDietView(View view) {

        Intent go = new Intent(this, DietAdd.class);
        int Result = 1;
        startActivityForResult(go, Result);

    }

    private void editDiets(Diet diet){
        Intent go = new Intent(this, DietEdit.class);
        go.putExtra("diet", diet);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            Diet diet = (Diet) data.getSerializableExtra("diet");
            boolean success = dataSource.updateDiet(diet);
            if(success) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == diet.getId()) {
                        items.set(i, diet);
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
                String startSentBack = data.getStringExtra("start");
                String endSentBack = data.getStringExtra("end");

                System.out.println(nameSentBack+" "+descriptionSentBack+" "+startSentBack+" "+endSentBack);

                Diet diet = dataSource.createDiet(nameSentBack, descriptionSentBack,startSentBack,
                        endSentBack);
                System.out.println(diet);
                items.add(diet);
                itemsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
