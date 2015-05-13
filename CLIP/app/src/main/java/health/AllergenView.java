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


public class AllergenView extends Activity {

    private AllergenDataSource dataSource;
    private ListView lv;
    private ArrayAdapter<Allergen> itemsAdapter;
    private List<Allergen> items;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergens);

        dataSource = new AllergenDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.allergenListView);
        items = dataSource.getAllAllergens();

        itemsAdapter = new ArrayAdapter<Allergen>(this,android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    private void setupListViewListener() {
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        editAllergens(items.get(pos));
                    }

                }
        );
        lv.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(AllergenView.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteAllergen(items.get(position).getId());
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
        getMenuInflater().inflate(R.menu.menu_allergens, menu);
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

    public void goToAddAllergenView(View view) {
        Intent go = new Intent(this, AllergenAdd.class);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    private void editAllergens(Allergen allergen){
        System.out.println("and here");
        Intent go = new Intent(this, AllergenEdit.class);
        go.putExtra("allergen", allergen);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            Allergen allergen = (Allergen) data.getSerializableExtra("allergen");
            boolean success = dataSource.updateAllergen(allergen);
            if(success) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == allergen.getId()) {
                        items.set(i, allergen);
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

                Allergen allergen = dataSource.createAllergen(nameSentBack, descriptionSentBack);
                items.add(allergen);
                itemsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

