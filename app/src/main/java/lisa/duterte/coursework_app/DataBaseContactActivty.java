package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseContactActivty extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactActivity.dbCA";
    private static final String CONTACT_ACITIVITY_NAME = "contact_activity_table";
    private static final String COL_1 = "PSEUDO";
    private static final String COL_2 = "ACTIVITY_NUMBER";


    public DataBaseContactActivty(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbCA) {
        dbCA.execSQL("create table " + CONTACT_ACITIVITY_NAME +  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PSEUDO TEXT, ACTIVITY_NUMBER INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase dbCA, int oldVersion, int newVersion) {
        dbCA.execSQL("DROP TABLE IF EXISTS " + CONTACT_ACITIVITY_NAME);
        onCreate(dbCA);
    }

    boolean insertContactActivity (String pseudo, Integer activity_number) {
        SQLiteDatabase dbCA = this.getWritableDatabase();
        ContentValues contentPseudo = new ContentValues();
        contentPseudo.put(COL_1,pseudo);
        contentPseudo.put(COL_2,activity_number);

        //Check si l'activité possède déjà ou pas encore le contact
        if (!checkPseudoContactActivity(pseudo,activity_number)) {
            long result = dbCA.insert(CONTACT_ACITIVITY_NAME, null, contentPseudo);
            if (result == -1) return false;
             else return true;
        }
        else return false;
    }

    public Cursor viewContactActivity(Integer activity_number) {
        SQLiteDatabase dbCA = this.getReadableDatabase();
        Cursor c = dbCA.rawQuery("SELECT * FROM contact_activity_table WHERE activity_number = '"+activity_number+"'", null);
        return  c;
    }

    private boolean checkPseudoContactActivity(String pseudo,Integer activity_number) {
        SQLiteDatabase dbCA = this.getReadableDatabase();

        String savePseudo = null;
        Cursor c = dbCA.rawQuery("SELECT * FROM contact_activity_table WHERE activity_number = '"+activity_number+"'", null);
        int pseudoIndex = c.getColumnIndex("PSEUDO");
        c.moveToFirst();
        if (c.moveToFirst()) {
            savePseudo = c.getString(pseudoIndex);
            Log.d("bddCA","savePseudo = " + savePseudo);
            if (savePseudo.equals(pseudo)){
                return true;
            }
            while (c.moveToNext()){
                savePseudo = c.getString(pseudoIndex);
                Log.d("bddCA","savePseudo = " + savePseudo);
                if (savePseudo.equals(pseudo)){
                    return true;
                }
            }
            c.close();
        }
        else {
            c.close();
            return false;
        }
        return false;
    }

    //A MODIFIER
    public Integer deleteData(String pseudo) {
        SQLiteDatabase dbCA = this.getWritableDatabase();
        return  dbCA.delete(CONTACT_ACITIVITY_NAME,"PSEUDO = ?",new String[] {pseudo});
    }

}
