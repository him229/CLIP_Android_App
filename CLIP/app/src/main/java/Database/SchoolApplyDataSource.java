package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import education.SchoolApply;

public class SchoolApplyDataSource {


    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DEGREE, DatabaseHelper.COL_AREA, DatabaseHelper.COL_COST,
            DatabaseHelper.COL_APPLICATION_COST, DatabaseHelper.COL_APPLICATION_DEADLINE};

    public SchoolApplyDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SchoolApply createSchoolApply(String name, String degree, String area, String cost,
                                         String appCost, String appDead) {

        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DEGREE, degree);
        values.put(dbHelper.COL_AREA, area);
        values.put(dbHelper.COL_COST, cost);
        values.put(dbHelper.COL_APPLICATION_COST, appCost);
        values.put(dbHelper.COL_APPLICATION_DEADLINE, appDead);
        long insertId = database.insert(dbHelper.TABLE_SCHOOLS_APPLIED, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_SCHOOLS_APPLIED,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        SchoolApply newSchoolApply = cursorToSchoolApply(cursor);
        cursor.close();
        return newSchoolApply;
    }

    public void deleteSchoolApply(long id) {
        database.delete(dbHelper.TABLE_SCHOOLS_APPLIED, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<SchoolApply> getAllSchoolApplys() {
        List<SchoolApply> schoolConsList = new ArrayList<SchoolApply>();

        Cursor cursor = database.query(dbHelper.TABLE_SCHOOLS_APPLIED,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SchoolApply schoolCons = cursorToSchoolApply(cursor);
            schoolConsList.add(schoolCons);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return schoolConsList;
    }

    private SchoolApply cursorToSchoolApply(Cursor cursor) {
        SchoolApply schoolCons = new SchoolApply();
        schoolCons.setId(cursor.getLong(0));
        schoolCons.setName(cursor.getString(1));
        schoolCons.setDegree(cursor.getString(2));
        schoolCons.setArea(cursor.getString(3));
        schoolCons.setCost(cursor.getString(4));
        schoolCons.setApplicationCost(cursor.getString(5));
        schoolCons.setAplicationDeadline(cursor.getString(6));
        return schoolCons;
    }
}