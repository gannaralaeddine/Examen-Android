package com.dsi31g13.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class RatingActivity extends AppCompatActivity {

    private static int RATING_RESULT = 300;
    private RatingBar wordRatingBar;
    private RatingBar excelRatingBar;
    private RatingBar powerpointRatingBar;
    private RatingBar outlookRatingBar;
    Button revalidateRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        wordRatingBar = findViewById(R.id.wordRatingBar);
        excelRatingBar = findViewById(R.id.excelRatingBar);
        powerpointRatingBar = findViewById(R.id.powerpointRatingBar);
        outlookRatingBar = findViewById(R.id.outlookRatingBar);
        revalidateRating = findViewById(R.id.revalidateRating);

        configureSharedPreferences();


    }

    private void configureSharedPreferences()
    {
        SharedPreferences mPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        String wordRating = mPreferences.getString("Word", null);
        String excelRating = mPreferences.getString("Excel", null);
        String powerpointRating = mPreferences.getString("PowerPoint", null);
        String outlookRating = mPreferences.getString("Outlook", null);

        //Display preferences
        if (wordRating != null)
        {
            wordRatingBar.setRating(Float.parseFloat(wordRating));
        }
        if (excelRating != null)
        {
            excelRatingBar.setRating(Float.parseFloat(excelRating));
        }
        if (powerpointRating != null)
        {
            powerpointRatingBar.setRating(Float.parseFloat(powerpointRating));
        }
        if (outlookRating != null)
        {
            outlookRatingBar.setRating(Float.parseFloat(outlookRating));
        }

        revalidateRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RATING_RESULT);
                finish();
            }
        });
    }
}
