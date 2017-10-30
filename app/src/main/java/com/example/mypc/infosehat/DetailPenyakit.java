package com.example.mypc.infosehat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailPenyakit extends AppCompatActivity {
    TextView textJudul, textDetail;
    ImageView imageView;
    SQLHelper sqlHelper;
    protected Cursor cursor;

    ZoomControls zoom;
    float x;

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

        x = textDetail.getTextSize();
        zoom.setOnZoomInClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int w = imageView.getWidth();
                int h = imageView.getHeight();

                textDetail.setTextSize(x++);

                RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(w + 10, h + 10);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);

                imageView.setLayoutParams(params);
            }
        });

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int w = imageView.getWidth();
                int h = imageView.getHeight();

                float b = textDetail.getTextSize();
                textDetail.setTextSize(x--);

                RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(w - 10, h - 10);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);

                imageView.setLayoutParams(params);
                //textDetail.setLayoutParams(params);
            }
        });

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
        zoom = (ZoomControls) findViewById(R.id.zoomControlsDetail);
    }
}
