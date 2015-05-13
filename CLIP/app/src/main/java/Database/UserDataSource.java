package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import project3310.financemenu.User;

/**
 * Created by somber on 5/5/2015.
 */
public class UserDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDataSource(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public User insertUser (User queryValues){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return queryValues;
    }

    public int updateUserPassword (User queryValues){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userId=database.insert("logins", null, values);
        database.close();
        return database.update("logins", values, "userId = ?", new String[] {String.valueOf(queryValues.userId)});
    }

    public User getUser (String username){
        String query = "Select userId, password from logins where username ='"+username+"'";
        User myUser = new User(0,username,"");
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userId=cursor.getLong(0);
                myUser.password=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myUser;
    }

    public void close() {
        dbHelper.close();
    }

    public void clear() {
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),1,2);
    }
}