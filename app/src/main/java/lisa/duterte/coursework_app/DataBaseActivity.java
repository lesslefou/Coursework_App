package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseActivity extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Activity.dbA";
    private static final String ACTIVITY_NAME = "activity_table";
    private static final String COL_1 = "NAME";
    private static final String COL_2 = "USER";
    private static final String COL_3 = "ADDRESS";


    public DataBaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbA) {
        dbA.execSQL("create table " + ACTIVITY_NAME + "(NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USER INTEGER, ADDRESS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbA, int oldVersion, int newVersion) {
        dbA.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_NAME);
        onCreate(dbA);
    }

    boolean insertActivity(Integer user, String name) {
        SQLiteDatabase dbA = this.getWritableDatabase();
        //String address = "";
        ContentValues contentActivity = new ContentValues();
        contentActivity.put(COL_1, name);
        contentActivity.put(COL_2, user);
       // contentActivity.put(COL_3, address);

        long result = dbA.insert(ACTIVITY_NAME, null, contentActivity);
        if (result == -1) return false;
        else return true;
    }

    boolean insertAddress(String address,Integer activity_number) {
        SQLiteDatabase dbA = this.getWritableDatabase();
        ContentValues contentActivity = new ContentValues();
        contentActivity.put(COL_3, address);

        dbA.update(ACTIVITY_NAME,contentActivity,"NUMBER = ?",new  String[] {activity_number.toString()});
        return true;
    }

    //Faire une fonction qui ajoute les SHARE

    public Integer deleteActivity(String name) {
        SQLiteDatabase dbA = this.getWritableDatabase();
        return dbA.delete(ACTIVITY_NAME, "NAME = ?", new String[]{name});
    }

    public Cursor viewActivity(Integer idUser) {
        SQLiteDatabase dbA = this.getReadableDatabase();
        Cursor c = dbA.rawQuery("SELECT * FROM activity_table WHERE user = '" + idUser + "'", null);
        return c;
    }

    public int numberActivityRecover(String nameActivity, Integer idUser) {
        SQLiteDatabase dbA = this.getReadableDatabase();

        int number = -1, userSave = -1;
        String nameSave = "";
        Cursor c = dbA.rawQuery("SELECT * FROM activity_table WHERE USER = '" + idUser + "'", null);

        int userIndex = c.getColumnIndex("USER");
        int nameIndex = c.getColumnIndex("NAME");
        int numberIndex = c.getColumnIndex("NUMBER");
        c.moveToFirst();
        if (c.moveToFirst()) {
            while (c.moveToNext()) {
                userSave = c.getInt(userIndex);
                nameSave = c.getString(nameIndex);
                number = c.getInt(numberIndex);
                if (nameSave.equals(nameActivity)) {
                    if (userSave == idUser) {
                        c.close();
                        return number;
                    }
                }
            }
            return -1;
        } else {
            c.close();
            return -1;
        }
    }
}
