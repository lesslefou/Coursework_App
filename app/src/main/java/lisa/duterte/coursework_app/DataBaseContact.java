package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseContact extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Contact.db";
    private static final String CONTACT_NAME = "contact_table";
    private static final String COL_1 = "PSEUDO";
    private static final String COL_2 = "USER";


    public DataBaseContact(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbC) {
        dbC.execSQL("create table " + CONTACT_NAME +  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PSEUDO TEXT,USER INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_NAME);
        onCreate(db);
    }

    boolean insertPseudo(String pseudo, Integer user) {
        SQLiteDatabase dbC = this.getWritableDatabase();
        ContentValues contentPseudo = new ContentValues();
        contentPseudo.put(COL_1,pseudo);
        contentPseudo.put(COL_2,user);
        if (!checkPseudoContact(pseudo)) {
            long result = dbC.insert(CONTACT_NAME, null, contentPseudo);
            if (result == -1) return false;
            else return true;
        }
        else return false;
    }

    public Cursor viewContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + CONTACT_NAME, null);
        return  res;
    }

    private boolean checkPseudoContact(String pseudo) {
        SQLiteDatabase db = this.getReadableDatabase();

        String savePseudo = null;
        Cursor c = db.rawQuery("SELECT * FROM contact_table WHERE pseudo = '"+pseudo+"'", null);
        int pseudoIndex = c.getColumnIndex("PSEUDO");
        c.moveToFirst();
        if (c.moveToFirst()) {
            savePseudo = c.getString(pseudoIndex);

            return savePseudo.equals(pseudo);
        }
        else return false;
    }

    public Integer deleteData(String pseudo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(CONTACT_NAME,"PSEUDO = ?",new String[] {pseudo});
    }
}
