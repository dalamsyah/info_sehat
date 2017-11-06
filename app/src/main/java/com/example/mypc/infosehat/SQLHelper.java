package com.example.mypc.infosehat;

/**
 * Created by My-PC on 27/06/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "infosehat.db";
    private static final int DATABASE_VERSION = 1;
    public SQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS penyakit (id INTEGER PRIMARY KEY AUTOINCREMENT, judul VARCHAR)";
        db.execSQL(sql);

        sql = "INSERT INTO penyakit (judul) VALUES " +
                "('ASMA'), " +
                "('DBD'), " +
                "('DIABETES')," +
                "('GINJAL')," +
                "('HIPERTENSI')," +
                "('LEPRA')," +
                "('KOLESTEROL')," +
                "('MALARIA')," +
                "('TBC')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
