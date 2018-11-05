package co.acuencadev.bakingapp.ui.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.ui.main.MainActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String RECIPE_ID_STATE = "recipeId";

    private int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getRecipeIdFromIntent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(RECIPE_ID_STATE, mRecipeId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mRecipeId = savedInstanceState.getInt(RECIPE_ID_STATE);
    }

    private void getRecipeIdFromIntent() {
        Intent intent = getIntent();

        mRecipeId = intent.getIntExtra(MainActivity.EXTRA_RECIPE_ID, 0);
    }
}
