package career;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import project3310.financemenu.R;


public class career extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_career, menu);
        return true;
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("hello","");
        startActivity(intent);
    }
    public void longTermClick(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
    public void jobSearch(View view){
        Intent intent = new Intent(this, JobSearchList.class);
        startActivity(intent);
    }
    public void companyInfo(View view){
        Intent intent = new Intent(this, CompanyInfoList.class);
        startActivity(intent);
    }
    public void networkClick(View view){
        Intent intent = new Intent(this, MainNetworkList.class);
        startActivity(intent);
    }
    public void electronicID(View view){
        Intent intent = new Intent(this, ElectronicIDList.class);
        startActivity(intent);
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
}
