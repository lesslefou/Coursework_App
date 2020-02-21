package lisa.duterte.coursework_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUser extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME = "user_table";
    private static final String COL_1 = "NAME";
    private static final String COL_2 = "SURNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PSEUDO";
    private static final String COL_5 = "PASSWORD";


    public DatabaseUser(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, PSEUDO TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean insertData(String name, String surname, String email, String pseudo, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,name);
        contentValues.put(COL_2,surname);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,pseudo);
        contentValues.put(COL_5,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1) return false;
        else return true;
    }


    public Cursor viewData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return  res;
    }

    int checkIfUserExist(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String savedEmail;
        String savedPassword;
        int idFound;

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
    }

    boolean checkPseudo(String pseudo) {
        SQLiteDatabase db = this.getReadableDatabase();

        String savePseudo = null;
        Cursor c = db.rawQuery("SELECT * FROM user_table WHERE pseudo = '"+pseudo+"'", null);
        int pseudoIndex = c.getColumnIndex("PSEUDO");
        c.moveToFirst();
        if (c.moveToFirst()) {
            savePseudo = c.getString(pseudoIndex);

            return savePseudo.equals(pseudo);
        }
        else return false;
    }

    String nameRecover(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String nameSave = null;

        Cursor c = db.rawQuery("SELECT * FROM user_table WHERE id = '"+id+"'", null);
        int nameIndex = c.getColumnIndex("NAME");
        c.moveToFirst();
        if (c.moveToFirst()) {
            return nameSave = c.getString(nameIndex);
        }
        else
            return "error";
    }
}
