package com.project.jsonpractice.jsonpractice;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.jsonpractice.jsonpractice.Model.ItunesStuff;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textArtistName;
    TextView textType;
    TextView textKind;
    TextView textCollectionName;
    TextView textTrackName;
    ImageView imageArt;
    Button buttonGetData;
    String imageURL;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textArtistName = (TextView) findViewById(R.id.textArtistName);
        textType = (TextView) findViewById(R.id.textType);
        textKind = (TextView) findViewById(R.id.textKind);
        textCollectionName = (TextView) findViewById(R.id.textCollectionName);
        textTrackName = (TextView) findViewById(R.id.textTrackName);
        imageArt = (ImageView) findViewById(R.id.imageArt);
        buttonGetData = (Button) findViewById(R.id.buttonGetData);

        buttonGetData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();
    }

    private class JSONItunesStuffTask extends AsyncTask<String , Void , ItunesStuff>
    {
        Context context;
        ProgressDialog progressDialog;

        public JSONItunesStuffTask(Context context)
        {
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog.setTitle("Downloading Info From Itunes..... PLease Wait");
            progressDialog.show();
        }

        @Override
        protected ItunesStuff doInBackground(String... params)
        {
            ItunesStuff itunesStuff = new ItunesStuff();

            String data = ((new ItunesHTTPClient()).getItuneStuffData());

            try
            {
                itunesStuff = JsonItunesParser.getItunesStuff(data);
                imageURL = itunesStuff.getArtistViewURL();
                bitmap = ((new ItunesHTTPClient()).getBitmapFromURL(imageURL));
            }
            catch (Throwable t)
            {
                t.printStackTrace();
            }
            return itunesStuff;
        }
    }
}
