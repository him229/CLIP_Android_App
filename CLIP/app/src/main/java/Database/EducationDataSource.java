package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.List;

import education.SchoolCons;

public class EducationDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_DESCRIPTION};

    public EducationDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void setCurrentEducation(String text) {

        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DESCRIPTION, text);
        database.update(dbHelper.TABLE_EDUCATION, values, dbHelper.COL_ID + "=" + dbHelper.ID_CURRENT_EDUCATION, null);
    }

    public void setGraduatePlans(String text) {

        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DESCRIPTION, text);
        database.update(dbHelper.TABLE_EDUCATION, values, dbHelper.COL_ID + "=" + dbHelper.ID_GRADUATE_PLANS, null);
    }

    public String getCurrentEducation() {
        Cursor cursor = database.query(dbHelper.TABLE_EDUCATION,
                allColumns, dbHelper.COL_ID + " = " + dbHelper.ID_CURRENT_EDUCATION, null,
                null, null, null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public String getGraduatePlans() {
        Cursor cursor = database.query(dbHelper.TABLE_EDUCATION,
                allColumns, dbHelper.COL_ID + " = " + dbHelper.ID_GRADUATE_PLANS, null,
                null, null, null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }
}
