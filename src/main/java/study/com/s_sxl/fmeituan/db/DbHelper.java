package study.com.s_sxl.fmeituan.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import study.com.s_sxl.fmeituan.bean.UserBean;

public class DbHelper {

   // private Context mContext;
   // private String mDbPath;
    private SQLiteDatabase mDb;
    private SQLiteOpenHelper mInstance;
    //private String mFilePath;

    /**
     * 初始化数据库
     * @param context
     */
    public void initSQLite(Context context){
        mInstance = DatabaseHelperUtil.getInstance(context);
        mInstance.getReadableDatabase();
    }

    public SQLiteDatabase getDb(){
        if(mDb == null){
            mDb = mInstance.getWritableDatabase();
        }
        return mDb;
    }

  /*  *//**
     * 复制db文件到系统目录中
     * @return
     *//*
    private SQLiteDatabase getDataBaseInstance() {
        File file = new File(mDbPath);
        if(file.exists()){
            return SQLiteDatabase.openOrCreateDatabase(mDbPath,null);
        }else {
            File fileStr = new File(mFilePath);
            fileStr.mkdir();

            try {
                InputStream in = mContext.getAssets().open(Constant.DBNAME);
                FileOutputStream out = new FileOutputStream(mDbPath);
                byte[] bytes = new byte[1024];
                int read = 0;
                while ((read = in.read(bytes)) != -1){
                    out.write(bytes,0,read);
                    in.close();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return SQLiteDatabase.openOrCreateDatabase(mDbPath,null);
    }*/

    /**
     * 添加用户信息
     * @param bean
     * @param str
     * @return
     */
    public boolean addUser(UserBean bean ,StringBuffer str){
        getDb();
        str.setLength(0);

        String sqlStr = String.format("insert into users(username,password,headImg,nickname,phoneNumber) values ('%s','%s','%s','%s','%s')",bean.userName,
                    bean.password,bean.headImg,bean.nickName,bean.phoneNumber);

        try {
            mDb.execSQL(sqlStr);
        }catch (Exception e){
            str.append(e.getMessage());
            return false;
        }

        return true;
    }


    /**
     * 通过用户的帐号与密码 获取用户信息
     * @param bean
     * @param str
     * @return
     */
    public boolean getUserInfo(UserBean bean ,StringBuffer str){
        getDb();
        str.setLength(0);

        Cursor cursor = null;

        //String sqlStr =String.format( "select * from users where password = %s and phoneNumber = %s",bean.password,bean.phoneNumber);
        String sqlStr = "select * from users";

        try {
            cursor = mDb.rawQuery(sqlStr,null);

            while (cursor.moveToNext()){
                bean.password = cursor.getString(cursor.getColumnIndex("password"));
                bean.phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            }
        }catch (Exception e){
            e.printStackTrace();
            str.append(e.getMessage());
            return false;
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return true;
    }
}
