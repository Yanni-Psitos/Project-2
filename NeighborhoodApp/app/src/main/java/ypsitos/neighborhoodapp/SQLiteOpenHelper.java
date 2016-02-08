package ypsitos.neighborhoodapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by YPsitos on 2/4/16.
 */
public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NEIGHBORHOOD_DB";
    public static final String TABLE_NAME = "LOCATION_LIST";
    public static final String COL_ID = "_id";
    public static final String COL_HERO_NAME = "HERO_NAME";
    public static final String COL_REAL_NAME = "REAL_NAME";
    public static final String COL_POWERS = "POWERS";
    public static final String COL_ADDITIONAL_DETAIL = "ADDITIONAL_DETAILS";
    public static final String COL_FAVORITED = "FAVORITED";

    public static final String[] HERO_COLUMNS = {COL_ID, COL_HERO_NAME, COL_REAL_NAME, COL_POWERS};

    private static final String CREATE_HERO_LIST_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HERO_NAME + " TEXT, " +
                    COL_REAL_NAME + " TEXT, " +
                    COL_POWERS + " TEXT, " +
                    COL_ADDITIONAL_DETAIL + " TEXT, "+
                    COL_FAVORITED+" TEXT)";

    public SQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HERO_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }
    public Cursor searchShoppingList(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                SHOPPING_COLUMNS, // b. column names
                COL_ITEM_NAME + " LIKE ?", // c. selections
                new String[]{"%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
}
