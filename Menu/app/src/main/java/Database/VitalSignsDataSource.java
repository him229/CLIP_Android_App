package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.VitalSigns;


public class VitalSignsDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_DATE,
            DatabaseHelper.COL_HEART_RATE, DatabaseHelper.COL_BREATH,
            DatabaseHelper.COL_BLOOD_PRESSURE, DatabaseHelper.COL_TEMPERATURE};

    public VitalSignsDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public VitalSigns createVitalSigns(String date, String heartRate, String breath, String bloodPressure,
                                       String temperature) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DATE, date);
        values.put(dbHelper.COL_HEART_RATE, heartRate);
        values.put(dbHelper.COL_BREATH, breath);
        values.put(dbHelper.COL_BLOOD_PRESSURE, bloodPressure);
        values.put(dbHelper.COL_TEMPERATURE, temperature);
        long insertId = database.insert(dbHelper.TABLE_VITAL_SIGNS, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_VITAL_SIGNS,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        VitalSigns newVitalSigns = cursorToVitalSigns(cursor);
        cursor.close();
        return newVitalSigns;
    }

    public boolean updateVitalSigns(VitalSigns vitalSigns) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DATE, vitalSigns.getDate());
        values.put(dbHelper.COL_HEART_RATE, vitalSigns.getHeartRate());
        values.put(dbHelper.COL_BREATH, vitalSigns.getBreath());
        values.put(dbHelper.COL_BLOOD_PRESSURE, vitalSigns.getBloodPressure());
        values.put(dbHelper.COL_TEMPERATURE, vitalSigns.getTemperature());
        return database.update(dbHelper.TABLE_VITAL_SIGNS, values, dbHelper.COL_ID + "=" + vitalSigns.getId(), null) > 0;
    }

    public void deleteVitalSigns(long id) {
        database.delete(dbHelper.TABLE_VITAL_SIGNS, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<VitalSigns> getAllVitalSignss() {
        List<VitalSigns> vitalSignsList = new ArrayList<VitalSigns>();

        Cursor cursor = database.query(dbHelper.TABLE_VITAL_SIGNS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            VitalSigns vitalSigns = cursorToVitalSigns(cursor);
            vitalSignsList.add(vitalSigns);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return vitalSignsList;
    }

    private VitalSigns cursorToVitalSigns(Cursor cursor) {
        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setId(cursor.getLong(0));
        vitalSigns.setDate(cursor.getString(1));
        vitalSigns.setHeartRate(cursor.getString(2));
        vitalSigns.setBreath(cursor.getString(3));
        vitalSigns.setBloodPressure(cursor.getString(4));
        vitalSigns.setTemperature(cursor.getString(5));
        return vitalSigns;
    }
}