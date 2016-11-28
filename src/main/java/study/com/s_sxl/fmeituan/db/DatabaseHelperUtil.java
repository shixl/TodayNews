package study.com.s_sxl.fmeituan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperUtil extends SQLiteOpenHelper {

    private static SQLiteOpenHelper mInstance;

    public static synchronized SQLiteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelperUtil(context, "news.db", null, 1);
        }
        return mInstance;
    }

    public DatabaseHelperUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateUserTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    private void CreateUserTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + "users" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username" + " TEXT,"
                + "password" +" TEXT,"
                + "headImg" + " TEXT,"
                + "nickname" + " TEXT,"
                + "phoneNumber" + "TEXT);");
    }
}
