package co.acuencadev.bakingapp.ui.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.ui.main.MainActivity;

public class DetailActivity extends AppCompatActivity {

    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getRecipeIdFromIntent();
    }

    private void getRecipeIdFromIntent() {
        Intent intent = getIntent();

        mRecipeId = intent.getIntExtra(MainActivity.EXTRA_RECIPE_ID, 0);
    }
}
