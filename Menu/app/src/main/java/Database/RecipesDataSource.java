package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import health.Recipes;


public class RecipesDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
            DatabaseHelper.COL_DESCRIPTION, DatabaseHelper.COL_URL};

    public RecipesDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Recipes createRecipes(String name, String description, String URL) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, name);
        values.put(dbHelper.COL_DESCRIPTION, description);
        values.put(dbHelper.COL_URL, URL);
        long insertId = database.insert(dbHelper.TABLE_RECIPES, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_RECIPES,
                allColumns, dbHelper.COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Recipes newRecipes = cursorToRecipes(cursor);
        cursor.close();
        return newRecipes;
    }

    public boolean updateRecipes(Recipes recipes) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_NAME, recipes.getName());
        values.put(dbHelper.COL_DESCRIPTION, recipes.getDescription());
        values.put(dbHelper.COL_URL, recipes.getURL());
        return database.update(dbHelper.TABLE_RECIPES, values, dbHelper.COL_ID + "=" + recipes.getId(), null) > 0;
    }

    public void deleteRecipes(long id) {
        database.delete(dbHelper.TABLE_RECIPES, dbHelper.COL_ID
                + " = " + id, null);
    }

    public List<Recipes> getAllRecipess() {
        List<Recipes> RecipesList = new ArrayList<Recipes>();

        Cursor cursor = database.query(dbHelper.TABLE_RECIPES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Recipes Recipes = cursorToRecipes(cursor);
            RecipesList.add(Recipes);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return RecipesList;
    }

    private Recipes cursorToRecipes(Cursor cursor) {
        Recipes Recipes = new Recipes();
        Recipes.setId(cursor.getLong(0));
        Recipes.setName(cursor.getString(1));
        Recipes.setDescription(cursor.getString(2));
        Recipes.setURL(cursor.getString(3));
        return Recipes;
    }
}