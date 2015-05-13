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

import Database.VitalSignsDataSource;
import project3310.financemenu.R;


public class VitalSignsView extends Activity {

    private VitalSignsDataSource dataSource;
    private ListView lv;
    private ArrayAdapter<VitalSigns> itemsAdapter;
    private List<VitalSigns> items;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs);

        dataSource = new VitalSignsDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.VitalSignsListView);
        items = dataSource.getAllVitalSignss();

        itemsAdapter = new ArrayAdapter<VitalSigns>(this,android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        editVitalSignss(items.get(pos));
                    }

                }
        );
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(VitalSignsView.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure you want to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteVitalSigns(items.get(position).getId());
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
        getMenuInflater().inflate(R.menu.menu_vital_signs, menu);
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

    public void goToAddVitalSignsView(View view) {

        Intent go = new Intent(this, VitalSignsAdd.class);
        int Result = 1;
        startActivityForResult(go, Result);

    }

    private void editVitalSignss(VitalSigns vitalSigns){
        Intent go = new Intent(this, VitalSignsEdit.class);
        go.putExtra("vitalSigns", vitalSigns);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            VitalSigns vitalSigns = (VitalSigns) data.getSerializableExtra("vitalSigns");
            boolean success = dataSource.updateVitalSigns(vitalSigns);
            if(success) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == vitalSigns.getId()) {
                        items.set(i, vitalSigns);
                        itemsAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

        }
        else if(resultCode == 1){

            try {
                String dateSentBack = data.getStringExtra("date");
                String HRSentBack = data.getStringExtra("HR");
                String breathSentBack = data.getStringExtra("breath");
                String BPSentBack = data.getStringExtra("BP");
                String temperatureSentBack = data.getStringExtra("temperature");

                VitalSigns vitalSigns = dataSource.createVitalSigns(dateSentBack, HRSentBack, breathSentBack,
                        BPSentBack, temperatureSentBack);
                items.add(vitalSigns);
                itemsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}