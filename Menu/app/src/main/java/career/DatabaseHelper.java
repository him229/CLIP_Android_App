package career;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by himankyadav on 5/5/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String LOG = "DatabaseHelper";

    public static final String DATABASE_NAME = "career.db";

    public static final String TABLE_ELECTRONICID = "electronicID_table";
    public static final String TABLE_JOBSEARCH = "jobsearch_table";
    public static final String TABLE_COMPANYINFO = "companyinfo_table";


    //Columns for Electronic ID
    public static final String EID_COL_1 = "ID";
    public static final String EID_COL_2 = "WEBSITE";
    public static final String EID_COL_3 = "USERID";
    public static final String EID_COL_4 = "PASSWORD";
    public static final String EID_COL_5 = "SECURITYQUES";
    public static final String EID_COL_6 = "ANSWER";

    //Columns for Job Search
    //public static final String JS_COL_1 = "ID";
    public static final String JS_COL_2 = "NAME";
    public static final String JS_COL_3 = "MONTH";
    public static final String JS_COL_4 = "STATUS";

   //Columns for Company Information List
    public static final String CI_COL_2 = "CI_NAME";
    public static final String CI_COL_3 = "CI_PRODUCT";
    public static final String CI_COL_4 = "CI_DETAILS";
    public static final String CI_COL_5 = "CI_FACTS";
    public static final String CI_COL_6 = "CI_REASON";
    public static final String CI_COL_7 = "CI_DATE";
    public static final String CI_COL_8 = "CI_INFO";


 //   SQLiteDatabase db = this.getWritableDatabase();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_ELECTRONICID+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, WEBSITE TEXT, USERID TEXT, PASSWORD TEXT, SECURITYQUES TEXT, ANSWER TEXT) ");
        db.execSQL("create table "+TABLE_JOBSEARCH+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MONTH TEXT, STATUS TEXT) ");
        db.execSQL("create table "+TABLE_COMPANYINFO+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, CI_NAME TEXT, CI_PRODUCT TEXT, CI_DETAILS TEXT, CI_FACTS TEXT, CI_REASON TEXT, CI_DATE TEXT, CI_INFO TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ELECTRONICID);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_JOBSEARCH);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COMPANYINFO);

        onCreate(db);
    }

    // Functions for ELECTRONIC ID

    public boolean insertDataEID(String website, String userID, String password, String securityQuestion, String answer ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EID_COL_2,website);
        contentValues.put(EID_COL_3,userID);
        contentValues.put(EID_COL_4,password);
        contentValues.put(EID_COL_5,securityQuestion);
        contentValues.put(EID_COL_6,answer);
        long result = db.insert(TABLE_ELECTRONICID,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getDataEID(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ELECTRONICID,null);
        return res;
    }
    public boolean deleteEID(String key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int boo = db.delete(TABLE_ELECTRONICID, EID_COL_3 + " = '" + key + "'" , null);
        if(boo<0){
            return false;
        }else {
            return true;
        }
    }

    // Functions for JOB SEARCH

    public boolean insertDataJS(String name, String month, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JS_COL_2,name);
        contentValues.put(JS_COL_3,month);
        contentValues.put(JS_COL_4,status);
        long result = db.insert(TABLE_JOBSEARCH,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getDataJS(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resJB = db.rawQuery("select * from " + TABLE_JOBSEARCH,null);
        return resJB;
    }
    public boolean deleteJS(String key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int boo = db.delete(TABLE_JOBSEARCH, JS_COL_2 + " = '" + key + "'" , null);
        if(boo<0){
            return false;
        }else {
            return true;
        }
    }

    //Functions for COMPANY INFORMATION

    public boolean insertDataCI(String name, String product, String details, String facts, String reason, String date, String info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CI_COL_2,name);
        contentValues.put(CI_COL_3,product);
        contentValues.put(CI_COL_4,details);
        contentValues.put(CI_COL_5,facts);
        contentValues.put(CI_COL_6,reason);
        contentValues.put(CI_COL_7,date);
        contentValues.put(CI_COL_8,info);
        long result = db.insert(TABLE_COMPANYINFO,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getDataCI(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resCI = db.rawQuery("select * from " + TABLE_COMPANYINFO,null);
        return resCI;
    }
    public boolean deleteCI(String key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int boo = db.delete(TABLE_COMPANYINFO, CI_COL_2 + " = '" + key + "'" , null);
        if(boo<0){
            return false;
        }else {
            return true;
        }
    }

}
