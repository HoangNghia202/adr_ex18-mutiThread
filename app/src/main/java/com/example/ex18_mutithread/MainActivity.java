package com.example.ex18_mutithread;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public  String URL="";
    EditText editText;
    Button button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittxt);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image);

        editText.setText("https://www.planetware.com/wpimages/2020/02/fr\n" +
                "ance-in-pictures-beautiful-places-to-photograph-eiffel-\n" +
                "tower.jpg");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = editText.getText().toString();
                new DownloadImage().execute(URL);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
           String imageURL = URL[0];
              Bitmap bitmap = null;
              try {
                    InputStream inputStream = new java.net.URL(imageURL).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
              }catch (Exception e){
                    e.printStackTrace();
              }
              return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
           imageView.setImageBitmap(result);
        }
    }
}
