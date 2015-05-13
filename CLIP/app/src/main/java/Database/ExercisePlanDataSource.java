package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.ExercisePlan;


public class ExercisePlanDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DESCRIPTION};

    public ExercisePlanDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ExercisePlan createExercisePlan(String name, String description) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DESCRIPTION, description);
        long insertId = database.insert(dbHelper.TABLE_EXERCISE_PLAN, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_EXERCISE_PLAN,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ExercisePlan newExercisePlan = cursorToExercisePlan(cursor);
        cursor.close();
        return newExercisePlan;
    }

    public boolean updateExercisePlan(ExercisePlan exercisePlan) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, exercisePlan.getName());
        values.put(dbHelper.COL_DESCRIPTION, exercisePlan.getDescription());
        return database.update(dbHelper.TABLE_EXERCISE_PLAN, values, dbHelper.COL_ID + "=" + exercisePlan.getId(), null) > 0;
    }

    public void deleteExercisePlan(long id) {
        database.delete(dbHelper.TABLE_EXERCISE_PLAN, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<ExercisePlan> getAllExercisePlans() {
        List<ExercisePlan> exercisePlanList = new ArrayList<ExercisePlan>();

        Cursor cursor = database.query(dbHelper.TABLE_EXERCISE_PLAN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExercisePlan exercisePlan = cursorToExercisePlan(cursor);
            exercisePlanList.add(exercisePlan);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return exercisePlanList;
    }

    private ExercisePlan cursorToExercisePlan(Cursor cursor) {
        ExercisePlan exercisePlan = new ExercisePlan();
        exercisePlan.setId(cursor.getLong(0));
        exercisePlan.setName(cursor.getString(1));
        exercisePlan.setDescription(cursor.getString(2));
        return exercisePlan;
    }
}