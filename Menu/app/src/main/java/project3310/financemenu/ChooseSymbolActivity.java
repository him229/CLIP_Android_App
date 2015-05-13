package project3310.financemenu;

/**
 * Created by AhadV on 4/22/15.
 */


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseSymbolActivity extends Activity {
    private liveStock helper;
    private String symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_stock);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_live_stock, menu);
        return true;
    }

    public void onBtnClicked(View v) {
        if (v.getId() == R.id.button1) {
            // handling the click now

            EditText sym = (EditText) findViewById(R.id.editText1);
            TextView tv = (TextView) findViewById(R.id.textView10);
            // tv.setText(sym.getText().toString());
            if (sym.getText().toString().equalsIgnoreCase("")) {
                tv.setText("Please Enter Symbol");
            } else {
                symbol = sym.getText().toString();
                helper = new liveStock(symbol);
                new ContactWebservice().execute(helper);
            }

            sym.setText("");

        }
    }

    private class ContactWebservice extends AsyncTask<liveStock, Void, String> {

        @Override
        protected String doInBackground(liveStock... params) {
            params[0].callService();
            return params[0].getName()+" is "+params[0].getLast();
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.textView10);
            txt.setText(result);

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}

