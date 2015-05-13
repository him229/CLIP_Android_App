package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.HealthNotes;


public class HealthNotesDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DESCRIPTION};

    public HealthNotesDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public HealthNotes createHealthNotes(String name, String description) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DESCRIPTION, description);
        long insertId = database.insert(dbHelper.TABLE_HEALTH_NOTES, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_HEALTH_NOTES,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        HealthNotes newHealthNotes = cursorToHealthNotes(cursor);
        cursor.close();
        return newHealthNotes;
    }

    public boolean updateHealthNotes(HealthNotes healthNotes) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, healthNotes.getName());
        values.put(dbHelper.COL_DESCRIPTION, healthNotes.getDescription());
        return database.update(dbHelper.TABLE_HEALTH_NOTES, values, dbHelper.COL_ID + "=" + healthNotes.getId(), null) > 0;
    }

    public void deleteHealthNotes(long id) {
        database.delete(dbHelper.TABLE_HEALTH_NOTES, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<HealthNotes> getAllHealthNotess() {
        List<HealthNotes> healthNotesList = new ArrayList<HealthNotes>();

        Cursor cursor = database.query(dbHelper.TABLE_HEALTH_NOTES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HealthNotes healthNotes = cursorToHealthNotes(cursor);
            healthNotesList.add(healthNotes);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return healthNotesList;
    }

    private HealthNotes cursorToHealthNotes(Cursor cursor) {
        HealthNotes healthNotes = new HealthNotes();
        healthNotes.setId(cursor.getLong(0));
        healthNotes.setName(cursor.getString(1));
        healthNotes.setDescription(cursor.getString(2));
        return healthNotes;
    }
}