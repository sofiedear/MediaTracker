package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;

import static co.miniforge.corey.mediatracker.MyListActivity.mediaExtra;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaItemDetailActivity extends AppCompatActivity {
    EditText title, description, url;
    Button save;
    Intent intent;

    JSONObject jsonData;
    MediaItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

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
