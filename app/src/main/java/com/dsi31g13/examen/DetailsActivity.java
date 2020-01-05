package com.dsi31g13.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.prefs.Preferences;

public class DetailsActivity extends AppCompatActivity {

    private static final int DETAILS_RESULT = 200;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    TextView fruit_title;
    ImageView fruit_image;
    TextView fruit_description;
    RatingBar ratingBar;
    Button validateRating;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        fruit_title = findViewById(R.id.fruit_title);
        fruit_image = findViewById(R.id.fruit_image);
        fruit_description  = findViewById(R.id.fruit_description);
        ratingBar = findViewById(R.id.ratingBar);
        validateRating = findViewById(R.id.validateRating);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey("title"))
        {
            title = bundle.getString("title");

            String[] titles = new String[]{getResources().getString(R.string.word),
                    getResources().getString(R.string.excel),
                    getResources().getString(R.string.powerpoint),
                    getResources().getString(R.string.outlook)};

            String[] descriptions = new String[]{getResources().getString(R.string.word_description),
                    getResources().getString(R.string.excel_description),
                    getResources().getString(R.string.powerpoint_description),
                    getResources().getString(R.string.outlook_description)};

            if (Objects.equals(title, titles[0]))
            {
                fruit_title.setText(title);
                fruit_description.setText(descriptions[0]);
                fruit_image.setImageResource(R.drawable.word);
            }
            else if (Objects.equals(title, titles[1]))
            {
                fruit_title.setText(title);
                fruit_description.setText(descriptions[1]);
                fruit_image.setImageResource(R.drawable.excel);
            }
            else if (Objects.equals(title, titles[2]))
            {
                fruit_title.setText(title);
                fruit_description.setText(descriptions[2]);
                fruit_image.setImageResource(R.drawable.powerpoint);
            }
            else if (Objects.equals(title, titles[3]))
            {
                fruit_title.setText(title);
                fruit_description.setText(descriptions[3]);
                fruit_image.setImageResource(R.drawable.outlook);
            }
            else
            {
                Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
            }

            mPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

            String rate = mPreferences.getString(title,"");
            checkSharedPreferences(title, rate);

            validateRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    configureSharedPreferences(title, String.valueOf(ratingBar.getRating()));
                    setResult(DETAILS_RESULT);
                    finish();
                }
            });
        }
    }

    private void configureSharedPreferences(String rateValue, String ratingValue)
    {
        mPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.putString(rateValue, ratingValue);
        mEditor.apply();
    }

    private void checkSharedPreferences(String title, String ratingValue)
    {
        if (!ratingValue.equals(""))
        {
            ratingBar.setRating(Float.parseFloat(ratingValue));
        }
    }
}
