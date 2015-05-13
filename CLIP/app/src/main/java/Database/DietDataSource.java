package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.Diet;


public class DietDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DESCRIPTION, DatabaseHelper.COL_START,
            DatabaseHelper.COL_END};

    public DietDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Diet createDiet(String name, String description, String start, String end) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DESCRIPTION, description);
        values.put(dbHelper.COL_START, start);
        values.put(dbHelper.COL_END, end);
        long insertId = database.insert(dbHelper.TABLE_DIET, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_DIET,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Diet newDiet = cursorToDiet(cursor);
        cursor.close();
        return newDiet;
    }

    public boolean updateDiet(Diet diet) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, diet.getName());
        values.put(dbHelper.COL_DESCRIPTION, diet.getDescription());
        values.put(dbHelper.COL_START, diet.getStart());
        values.put(dbHelper.COL_END, diet.getEnd());
        return database.update(dbHelper.TABLE_DIET, values, dbHelper.COL_ID + "=" + diet.getId(), null) > 0;
    }

    public void deleteDiet(long id) {
        database.delete(dbHelper.TABLE_DIET, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<Diet> getAllDiets() {
        List<Diet> dietList = new ArrayList<Diet>();

        Cursor cursor = database.query(dbHelper.TABLE_DIET,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Diet diet = cursorToDiet(cursor);
            dietList.add(diet);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dietList;
    }

    private Diet cursorToDiet(Cursor cursor) {
        Diet diet = new Diet();
        diet.setId(cursor.getLong(0));
        diet.setName(cursor.getString(1));
        diet.setDescription(cursor.getString(2));
        diet.setStart(cursor.getString(3));
        diet.setEnd(cursor.getString(4));
        return diet;
    }
}