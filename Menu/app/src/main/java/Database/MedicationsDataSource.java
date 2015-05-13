package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.Medications;


public class MedicationsDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DOSAGE, DatabaseHelper.COL_START,
            DatabaseHelper.COL_END};

    public MedicationsDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Medications createMedications(String name, String dosage, String start, String end) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DOSAGE, dosage);
        values.put(dbHelper.COL_START, start);
        values.put(dbHelper.COL_END, end);
        long insertId = database.insert(dbHelper.TABLE_MEDICATION, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_MEDICATION,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Medications newMedications = cursorToMedications(cursor);
        cursor.close();
        return newMedications;
    }

    public boolean updateMedications(Medications medications) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, medications.getName());
        values.put(dbHelper.COL_DOSAGE, medications.getDosage());
        values.put(dbHelper.COL_START, medications.getStart());
        values.put(dbHelper.COL_END, medications.getEnd());
        return database.update(dbHelper.TABLE_MEDICATION, values, dbHelper.COL_ID + "=" + medications.getId(), null) > 0;
    }

    public void deleteMedications(long id) {
        database.delete(dbHelper.TABLE_MEDICATION, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<Medications> getAllMedications() {
        List<Medications> medicationsList = new ArrayList<Medications>();

        Cursor cursor = database.query(dbHelper.TABLE_MEDICATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medications medications = cursorToMedications(cursor);
            medicationsList.add(medications);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return medicationsList;
    }

    private Medications cursorToMedications(Cursor cursor) {
        Medications medications = new Medications();
        medications.setId(cursor.getLong(0));
        medications.setName(cursor.getString(1));
        medications.setDosage(cursor.getString(2));
        medications.setStart(cursor.getString(3));
        medications.setEnd(cursor.getString(4));
        return medications;
    }
}