package com.example.mypc.infosehat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.widget.ZoomControls;

public class Tentang extends AppCompatActivity {
    TextView textDetail;
    ImageView imageView;
    ZoomControls zoom;
    float x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
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
        InputStream is = this.getAssets().open( "TENTANG.txt" );
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();
        textDetail.setText(buf.toString());

        int id = getResources().getIdentifier("com.example.mypc.infosehat:drawable/tentang", null, null);
        imageView.setImageResource(id);
    }
    private void init(){
        textDetail = (TextView)findViewById(R.id.txtdetailtentang);
        imageView = (ImageView)findViewById(R.id.imageViewtentang);
        zoom = (ZoomControls) findViewById(R.id.zoomControlsTentang);
    }
}
