package com.example.mypc.infosehat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailPenyakit extends AppCompatActivity {
    TextView textJudul, textDetail;
    ImageView imageView;
    SQLHelper sqlHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);

        init();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sqlHelper = new SQLHelper(this);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM penyakit WHERE judul = '" +
                getIntent().getStringExtra("judul") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);

            textJudul.setText(cursor.getString(1).toString());
            int id = getResources().getIdentifier("com.example.mypc.infosehat:drawable/"+ getIntent().getStringExtra("judul").toLowerCase(), null, null);
            imageView.setImageResource(id);
        }

        try {
            openFileText();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void openFileText() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getAssets().open( getIntent().getStringExtra("judul")+".txt" );
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();
        textDetail.setText(buf.toString());
    }

    private void init(){
        textJudul = (TextView)findViewById(R.id.txtjudultentang);
        textDetail = (TextView)findViewById(R.id.txtdetail);
        imageView = (ImageView)findViewById(R.id.imageViewtentang);
    }
}
