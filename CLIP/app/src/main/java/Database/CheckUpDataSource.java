package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.CheckUp;

public class CheckUpDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_DATE,
            DatabaseHelper.COL_CHOLESTEROL, DatabaseHelper.COL_BLOOD_PRESSURE,
            DatabaseHelper.COL_WEIGHT, DatabaseHelper.COL_TESTS};

    public CheckUpDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CheckUp createCheckUp(String date, String chol, String BP, String weight, String tests) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DATE, date);
        values.put(dbHelper.COL_CHOLESTEROL, chol);
        values.put(dbHelper.COL_BLOOD_PRESSURE, BP);
        values.put(dbHelper.COL_WEIGHT, weight);
        values.put(dbHelper.COL_TESTS, tests);
        long insertId = database.insert(dbHelper.TABLE_CHECK_UP, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_CHECK_UP,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CheckUp newCheckUp = cursorToCheckUp(cursor);
        cursor.close();
        return newCheckUp;
    }

    public boolean updateCheckUp(CheckUp checkUp) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DATE, checkUp.getDate());
        values.put(dbHelper.COL_CHOLESTEROL, checkUp.getCholesterol());
        values.put(dbHelper.COL_BLOOD_PRESSURE, checkUp.getBloodPressure());
        values.put(dbHelper.COL_WEIGHT, checkUp.getWeight());
        values.put(dbHelper.COL_TESTS, checkUp.getTests());
        return database.update(dbHelper.TABLE_CHECK_UP, values, dbHelper.COL_ID + "=" + checkUp.getId(), null) > 0;
    }

    public void deleteCheckUp(long id) {
        database.delete(dbHelper.TABLE_CHECK_UP, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<CheckUp> getAllCheckUps() {
        List<CheckUp> checkUpList = new ArrayList<CheckUp>();

        Cursor cursor = database.query(dbHelper.TABLE_CHECK_UP,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CheckUp checkUp = cursorToCheckUp(cursor);
            checkUpList.add(checkUp);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return checkUpList;
    }

    private CheckUp cursorToCheckUp(Cursor cursor) {
        CheckUp checkup = new CheckUp();
        checkup.setId(cursor.getLong(0));
        checkup.setDate(cursor.getString(1));
        checkup.setCholesterol(cursor.getString(2));
        checkup.setBloodPressure(cursor.getString(3));
        checkup.setWeight(cursor.getString(4));
        checkup.setTests(cursor.getString(5));
        return checkup;
    }
}
