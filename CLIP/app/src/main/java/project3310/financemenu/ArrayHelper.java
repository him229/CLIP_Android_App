package project3310.financemenu;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ArrayHelper {
    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public ArrayHelper(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    /**
     * Converts the provided ArrayList<String>
     * into a JSONArray and saves it as a single
     * string in the apps shared preferences
     * @param String key Preference key for SharedPreferences
     * @param array ArrayList<String> containing the list items
     */
    public void saveArray(String key, ArrayList<String> array) {
        JSONArray jArray = new JSONArray(array);
        editor.remove(key);
        editor.putString(key, jArray.toString());
        editor.commit();
    }

    /**
     * Loads a JSONArray from shared preferences
     * and converts it to an ArrayList<String>
     * @param String key Preference key for SharedPreferences
     * @return ArrayList<String> containing the saved values from the JSONArray
     */
    public ArrayList<String> getArray(String key) {
        ArrayList<String> array = new ArrayList<String>();
        String jArrayString = prefs.getString(key, "NOPREFSAVED");
        if (jArrayString.matches("NOPREFSAVED")) return getDefaultArray();
        else {
            try {
                JSONArray jArray = new JSONArray(jArrayString);
                for (int i = 0; i < jArray.length(); i++) {
                    array.add(jArray.getString(i));
                }
                return array;
            } catch (JSONException e) {
                return getDefaultArray();
            }
        }
    }

    // Get a default array in the event that there is no array
    // saved or a JSONException occurred
    private ArrayList<String> getDefaultArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Example 1");
        array.add("Example 2");
        array.add("Example 3");

        return array;
    }


}
