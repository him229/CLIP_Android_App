package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.List;

import health.Allergen;


public class DatabaseHelper extends SQLiteOpenHelper {

    // DATABASE

    public static final String DATABASE_NAME = "Clip.db";

    // TABLES

    public static final String TABLE_ALLERGEN = "allergen_table";
    public static final String TABLE_CHECK_UP = "check_up_table";
    public static final String TABLE_DIET = "diet_table";
    public static final String TABLE_EXERCISE_PLAN = "exercise_plan_table";
    public static final String TABLE_HEALTH_NOTES = "health_noet_table";
    public static final String TABLE_MEDICATION = "medication_table";
    public static final String TABLE_RECIPES = "recipes_table";
    public static final String TABLE_VITAL_SIGNS = "vital_signs_table";
    public static final String TABLE_EDUCATION = "education_table";
    public static final String TABLE_FINANCIAL_AID = "financial_aid_table";
    public static final String TABLE_SCHOOLS_APPLIED = "schools_applied_table";
    public static final String TABLE_SCHOOLS_CONSIDERING = "schools_considering_table";

    // COLUMNS

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_DATE = "DATE";
    public static final String COL_CHOLESTEROL = "CHOLESTEROL";
    public static final String COL_BLOOD_PRESSURE = "BLOOD_PRESSURE";
    public static final String COL_WEIGHT = "WEIGHT";
    public static final String COL_TESTS = "TESTS";
    public static final String COL_START = "START";
    public static final String COL_END = "END";
    public static final String COL_DOSAGE = "DOSAGE";
    public static final String COL_URL = "URL";
    public static final String COL_HEART_RATE = "HEART_RATE";
    public static final String COL_BREATH = "BREATH";
    public static final String COL_TEMPERATURE = "TEMPERATURE";
    public static final String COL_COST = "COST";
    public static final String COL_DEGREE = "DEGREE";
    public static final String COL_AREA = "AREA";
    public static final String COL_APPLICATION_COST = "APPLICATION_COST";
    public static final String COL_APPLICATION_DEADLINE = "APPLICATION_DEADLINE";
    public static final String COL_LOAN = "LOAN";
    public static final String COL_SCHOLARSHIP = "SCHOLARSHIP";
    public static final String COL_GRANT = "GRANT";

    // ID's

    public static final int ID_CURRENT_EDUCATION = 1;
    public static final int ID_GRADUATE_PLANS = 2;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // USER TABLE

        db.execSQL("create table logins (userId Integer primary key autoincrement, "+
                " username text, password text)");

        // EDUCATION TABLE

        db.execSQL("create table " + TABLE_EDUCATION + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_DESCRIPTION + " TEXT)");
        initEducationTable(db);

        db.execSQL("create table " + TABLE_FINANCIAL_AID + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_LOAN + " TEXT," + COL_SCHOLARSHIP + " TEXT," + COL_GRANT + " TEXT)");

        db.execSQL("create table " + TABLE_SCHOOLS_APPLIED + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DEGREE + " TEXT," + COL_AREA + " TEXT,"
                + COL_COST + " TEXT," + COL_APPLICATION_COST + " TEXT," + COL_APPLICATION_DEADLINE + " TEXT)");

        db.execSQL("create table " + TABLE_SCHOOLS_CONSIDERING + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DEGREE + " TEXT," + COL_AREA + " TEXT," + COL_COST + " TEXT)");

        // HEALTH TABLES

        db.execSQL("create table " + TABLE_ALLERGEN + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DESCRIPTION + " TEXT)");

        db.execSQL("create table " + TABLE_CHECK_UP + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_DATE + " TEXT," + COL_CHOLESTEROL + " TEXT," + COL_BLOOD_PRESSURE + " TEXT,"
                + COL_WEIGHT + " TEXT," + COL_TESTS + " TEXT)");

        db.execSQL("create table " + TABLE_DIET + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DESCRIPTION + " TEXT," + COL_START + " TEXT," + COL_END + " TEXT)");

        db.execSQL("create table " + TABLE_EXERCISE_PLAN + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DESCRIPTION + " TEXT)");

        db.execSQL("create table " + TABLE_HEALTH_NOTES + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DESCRIPTION + " TEXT)");

        db.execSQL("create table " + TABLE_MEDICATION + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DOSAGE + " TEXT," + COL_START + " TEXT," + COL_END + " TEXT)");

        db.execSQL("create table " + TABLE_RECIPES + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT," + COL_DESCRIPTION + " TEXT," + COL_URL + " TEXT)");

        db.execSQL("create table " + TABLE_VITAL_SIGNS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_DATE + " TEXT," + COL_HEART_RATE + " TEXT," + COL_BREATH + " TEXT,"
                + COL_BLOOD_PRESSURE + " TEXT," + COL_TEMPERATURE + " TEXT)");
    }

    private void initEducationTable(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(COL_DESCRIPTION, "None");
        db.insert(TABLE_EDUCATION,null,values);
        db.insert(TABLE_EDUCATION,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // USER TABLE

        db.execSQL("DROP TABLE IF EXISTS logins");

        // HEALTH TABLES

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ALLERGEN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHECK_UP);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DIET);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EXERCISE_PLAN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HEALTH_NOTES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MEDICATION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VITAL_SIGNS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EDUCATION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FINANCIAL_AID);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCHOOLS_APPLIED);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCHOOLS_CONSIDERING);

        onCreate(db);
    }

}