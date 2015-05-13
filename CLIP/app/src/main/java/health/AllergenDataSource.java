package health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHelper;


public class AllergenDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID,
            DatabaseHelper.COL_NAME, DatabaseHelper.COL_DESCRIPTION};

    public AllergenDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Allergen createAllergen(String name, String description) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DESCRIPTION, description);
        long insertId = database.insert(dbHelper.TABLE_ALLERGEN, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_ALLERGEN,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Allergen newAllergen = cursorToAllergen(cursor);
        cursor.close();
        return newAllergen;
    }

    public boolean updateAllergen(Allergen allergen) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, allergen.getName());
        values.put(dbHelper.COL_DESCRIPTION, allergen.getDescription());
        return database.update(dbHelper.TABLE_ALLERGEN, values, dbHelper.COL_ID + "=" + allergen.getId(), null) > 0;
    }

    public void deleteAllergen(long id) {
        System.out.println("got here");
        database.delete(dbHelper.TABLE_ALLERGEN, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<Allergen> getAllAllergens() {
        List<Allergen> allergenList = new ArrayList<Allergen>();

        Cursor cursor = database.query(dbHelper.TABLE_ALLERGEN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Allergen allergen = cursorToAllergen(cursor);
            allergenList.add(allergen);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return allergenList;
    }

    private Allergen cursorToAllergen(Cursor cursor) {
        Allergen allergen = new Allergen();
        allergen.setId(cursor.getLong(0));
        allergen.setName(cursor.getString(1));
        allergen.setDescription(cursor.getString(2));
        return allergen;
    }
}
