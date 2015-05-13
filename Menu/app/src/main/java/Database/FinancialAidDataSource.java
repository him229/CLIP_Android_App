package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import education.FinancialAid;

public class FinancialAidDataSource {


    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_LOAN,
            DatabaseHelper.COL_SCHOLARSHIP, DatabaseHelper.COL_GRANT};

    public FinancialAidDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public FinancialAid createFinancialAid(String loan, String scholarship, String grant) {

        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_LOAN, loan);
        values.put(dbHelper.COL_SCHOLARSHIP, scholarship);
        values.put(dbHelper.COL_GRANT, grant);
        long insertId = database.insert(dbHelper.TABLE_FINANCIAL_AID, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_FINANCIAL_AID,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        FinancialAid newFinancialAid = cursorToFinancialAid(cursor);
        cursor.close();
        return newFinancialAid;
    }

    public void deleteFinancialAid(long id) {
        database.delete(dbHelper.TABLE_FINANCIAL_AID, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<FinancialAid> getAllFinancialAids() {
        List<FinancialAid> aidList = new ArrayList<FinancialAid>();

        Cursor cursor = database.query(dbHelper.TABLE_FINANCIAL_AID,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FinancialAid aid = cursorToFinancialAid(cursor);
            aidList.add(aid);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return aidList;
    }

    private FinancialAid cursorToFinancialAid(Cursor cursor) {
        FinancialAid aid = new FinancialAid();
        aid.setId(cursor.getLong(0));
        aid.setLoan(cursor.getString(1));
        aid.setScholarship(cursor.getString(2));
        aid.setGrants(cursor.getString(3));
        return aid;
    }
}