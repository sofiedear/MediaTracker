package co.miniforge.corey.mediatracker.media_recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;

import static co.miniforge.corey.mediatracker.MyListActivity.mediaExtra;

/**
 * Created by sofiyaantonyuk on 10/28/17.
 */

class MediaDetailActivity extends AppCompatActivity {

    EditText title, description, url;
    Button save;
    Intent intent;

    JSONObject jsonData;
    MediaItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        locateViews();
        getIntentData();
        bindData();
        turnOnClickListener();
    }

    private void locateViews() {
        url = (EditText) findViewById(R.id.url);
        description = (EditText) findViewById(R.id.description);
        title = (EditText) findViewById(R.id.title);

        save = (Button) findViewById(R.id.save);
    }

    public void getIntentData() {
        if (getIntent().hasExtra(MyListActivity.mediaExtra)) {
            try {
                jsonData = new JSONObject(getIntent().getStringExtra(MyListActivity.mediaExtra));

                item = new MediaItem(jsonData);

                title.setText(item.title);
                description.setText(item.description);
                url.setText(item.url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindData() {
        url.setText(item.url);
        description.setText(item.description);
        title.setText(item.title);
    }

    private void turnOnClickListener() {
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                item.title = title.getText().toString();
                item.description = description.getText().toString();
                item.url = url.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                intent.putExtra(mediaExtra, item.toJson().toString());

                startActivity(intent);
                //finish();
            }
        });
    }
}
