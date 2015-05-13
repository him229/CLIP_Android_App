package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import education.SchoolCons;

public class SchoolConsDataSource {


    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DEGREE, DatabaseHelper.COL_AREA,
            DatabaseHelper.COL_COST};

    public SchoolConsDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SchoolCons createSchoolCons(String name, String degree, String area, String cost) {

        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DEGREE, degree);
        values.put(dbHelper.COL_AREA, area);
        values.put(dbHelper.COL_COST, cost);
        long insertId = database.insert(dbHelper.TABLE_SCHOOLS_CONSIDERING, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_SCHOOLS_CONSIDERING,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        SchoolCons newSchoolCons = cursorToSchoolCons(cursor);
        cursor.close();
        return newSchoolCons;
    }

    public void deleteSchoolCons(long id) {
        database.delete(dbHelper.TABLE_SCHOOLS_CONSIDERING, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<SchoolCons> getAllSchoolConss() {
        List<SchoolCons> schoolConsList = new ArrayList<SchoolCons>();

        Cursor cursor = database.query(dbHelper.TABLE_SCHOOLS_CONSIDERING,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SchoolCons schoolCons = cursorToSchoolCons(cursor);
            schoolConsList.add(schoolCons);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return schoolConsList;
    }

    private SchoolCons cursorToSchoolCons(Cursor cursor) {
        SchoolCons schoolCons = new SchoolCons();
        schoolCons.setId(cursor.getLong(0));
        schoolCons.setName(cursor.getString(1));
        schoolCons.setDegree(cursor.getString(2));
        schoolCons.setArea(cursor.getString(3));
        schoolCons.setCost(cursor.getString(4));
        return schoolCons;
    }
}