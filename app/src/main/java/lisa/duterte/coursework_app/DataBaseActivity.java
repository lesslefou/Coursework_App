package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseActivity extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Activity.dbA";
    private static final String ACTIVITY_NAME = "activity_table";
    private static final String COL_1 = "NAME";
    private static final String COL_2 = "USER";
    private static final String COL_3 = "SHARE";


    public DataBaseActivity(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbA) {
        dbA.execSQL("create table " + ACTIVITY_NAME +  "(NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USER INTEGER, SHARE INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase dbA, int oldVersion, int newVersion) {
        dbA.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_NAME);
        onCreate(dbA);
    }

    boolean insertActivity(Integer user,String name) {
        SQLiteDatabase dbA = this.getWritableDatabase();
        ContentValues contentActivity = new ContentValues();
        contentActivity.put(COL_1,name);
        contentActivity.put(COL_2,user);

        long result = dbA.insert(ACTIVITY_NAME, null, contentActivity);
        if (result == -1) return false;
        else return true;
    }

    //Faire une fonction qui ajoute les SHARE

    public Integer deleteActivity(String name) {
        SQLiteDatabase dbA = this.getWritableDatabase();
        return  dbA.delete(ACTIVITY_NAME,"NAME = ?",new String[] {name});
    }

    public Cursor viewActivity(Integer idUser) {
        SQLiteDatabase dbA = this.getReadableDatabase();
        Cursor c = dbA.rawQuery("SELECT * FROM activity_table WHERE user = '"+idUser+"'", null);
        return  c;
    }
}
