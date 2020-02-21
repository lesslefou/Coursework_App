package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseDrinkFood extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChoiceDF.dbDF";
    private static final String ACTIVITY_NAME = "choiceDF_table";
    private static final String COL_1 = "NAME";
    private static final String COL_2 = "QUANTITY";
    private static final String COL_3 = "ACTIVITY_NUMBER";
    private static final String COL_4 = "TYPE";


    public DataBaseDrinkFood(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbDF) {
        dbDF.execSQL("create table " + ACTIVITY_NAME +  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, QUANTITY INTEGER, ACTIVITY_NUMBER INTEGER, TYPE INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase dbDF, int oldVersion, int newVersion) {
        dbDF.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_NAME);
        onCreate(dbDF);
    }

    boolean insertChoice(String name, Integer quantity, Integer activity_number, Integer type) {
        SQLiteDatabase dbDF = this.getWritableDatabase();
        ContentValues contentPseudo = new ContentValues();
        contentPseudo.put(COL_1,name);
        contentPseudo.put(COL_2,quantity);
        contentPseudo.put(COL_3,activity_number);
        contentPseudo.put(COL_4,type);




        long result = dbDF.insert(ACTIVITY_NAME, null, contentPseudo);
        if (result == -1) return false;
        else return true;
    }


    public Integer deleteData(String name) {
        SQLiteDatabase dbDF = this.getWritableDatabase();
        return  dbDF.delete(ACTIVITY_NAME,"NAME = ?",new String[] {name});
    }
}
