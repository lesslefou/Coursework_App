package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseContact extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Contact.dbC";
    private static final String CONTACT_NAME = "contact_table";
    private static final String COL_1 = "PSEUDO";
    private static final String COL_2 = "USER";


    public DataBaseContact(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase dbC) {
        dbC.execSQL("create table " + CONTACT_NAME +  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PSEUDO TEXT, USER INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase dbC, int oldVersion, int newVersion) {
        dbC.execSQL("DROP TABLE IF EXISTS " + CONTACT_NAME);
        onCreate(dbC);
    }

    boolean insertPseudo(String pseudo, Integer user) {
        SQLiteDatabase dbC = this.getWritableDatabase();
        ContentValues contentPseudo = new ContentValues();
        contentPseudo.put(COL_1,pseudo);
        contentPseudo.put(COL_2,user);
        if (!checkPseudoContact(pseudo,user)) {
            long result = dbC.insert(CONTACT_NAME, null, contentPseudo);
            if (result == -1) return false;
            else return true;
        }
        else return false;
    }

    public Cursor viewContact(Integer idUser) {
        SQLiteDatabase dbC = this.getReadableDatabase();
        Cursor c = dbC.rawQuery("SELECT * FROM contact_table WHERE user = '"+idUser+"'", null);
        return  c;
    }

    private boolean checkPseudoContact(String pseudo,Integer idUser) {
        SQLiteDatabase dbC = this.getReadableDatabase();

        String savePseudo = null;
        int check=0;
        Cursor c = dbC.rawQuery("SELECT * FROM contact_table WHERE user = '"+idUser+"'", null);
        int userIndex = c.getColumnIndex("USER");
        c.moveToFirst();
        if (c.moveToFirst()) {
            while (c.moveToNext()){
                savePseudo = c.getString(userIndex);
                if (savePseudo.equals(pseudo)){
                    check = 1;
                }
            }
        }
        else {
            return false;
        }
        if (check == 1) return true;
        else return false;
    }


    /*
    Cursor c = db.rawQuery("SELECT * FROM user_table WHERE email = '"+email+"'", null);
        int emailIndex = c.getColumnIndex("EMAIL");
        int passwordIndex = c.getColumnIndex("PASSWORD");
        int idIndex = c.getColumnIndex("ID");
        c.moveToFirst();
        if (c.moveToFirst()) {
            savedEmail = c.getString(emailIndex);
            savedPassword = c.getString(passwordIndex);
            idFound = c.getInt(idIndex);
            Log.d("BddUser", "Id = " + idFound);

            if (savedEmail.equals(email)){
                if (savedPassword.equals(password)) {
                    return idFound;
                }
                else return -1;
            }
            else return -1;
        }
        else return -1;
     */

    public Integer deleteData(String pseudo) {
        SQLiteDatabase dbC = this.getWritableDatabase();
        return  dbC.delete(CONTACT_NAME,"PSEUDO = ?",new String[] {pseudo});
    }
}
