package com.dsi31g13.examen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private static final int DETAILS_REQUEST = 100;
    private static final int DETAILS_RESULT = 200;
    private static final int RATING_RESULT = 300;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        String[] icons = new String[]{String.valueOf(R.drawable.word),
                String.valueOf(R.drawable.excel),
                String.valueOf(R.drawable.powerpoint),
                String.valueOf(R.drawable.outlook)};

        String[] titles = new String[]{getResources().getString(R.string.word),
                getResources().getString(R.string.excel),
                getResources().getString(R.string.powerpoint),
                getResources().getString(R.string.outlook)};

        String[] descriptions = new String[]{getResources().getString(R.string.word_description),
                getResources().getString(R.string.excel_description),
                getResources().getString(R.string.powerpoint_description),
                getResources().getString(R.string.outlook_description)};

        ArrayList <HashMap<String, String>> listItems = new ArrayList<>();
        HashMap <String, String> item = new HashMap<>();

        for (int i=0; i<4; i++)
        {
            item = new HashMap<>();
            item.put("icon", icons[i]);
            item.put("title", titles[i]);
            item.put("description", descriptions[i]);
            listItems.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(),
                listItems,
                R.layout.activity_list_item,
                new String[]{"title", "description", "icon"},
                new int[]{R.id.title, R.id.description, R.id.icon});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap item = (HashMap) listView.getItemAtPosition(i);

                    Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title",(String)item.get("title"));
                    intent.putExtras(bundle);
                    startActivityForResult(intent, DETAILS_REQUEST);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent rating_intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivityForResult(rating_intent, DETAILS_REQUEST);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILS_REQUEST)
        {
            if (resultCode == DETAILS_RESULT)
            {
                Log.e("MainActivity", "requestCode"+requestCode);
                Toast.makeText(MainActivity.this, "Rating avec succées", Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RATING_RESULT)
            {
                Log.e("MainActivity", "requestCode"+requestCode);
                Toast.makeText(MainActivity.this, "Vous povuez changer vos préférences", Toast.LENGTH_SHORT).show();
            }
        }
    }
}