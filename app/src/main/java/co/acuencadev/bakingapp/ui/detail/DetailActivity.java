package co.acuencadev.bakingapp.ui.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.ActivityDetailBinding;
import co.acuencadev.bakingapp.ui.detail.ingredients.IngredientListFragment;
import co.acuencadev.bakingapp.ui.detail.steps.StepListFragment;
import co.acuencadev.bakingapp.ui.main.MainActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String RECIPE_ID_STATE = "recipeId";

    private int mRecipeId;

    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        getRecipeIdFromIntent();

        setUpViewPager();
        setUpTabLayout();
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

    private void setUpViewPager() {
        RecipesViewPagerAdapter adapter = new RecipesViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IngredientListFragment(), "Ingredients");
        adapter.addFragment(new StepListFragment(), "Steps");

        mBinding.recipesDetailViewpager.setAdapter(adapter);
    }

    private void setUpTabLayout() {
        mBinding.recipesDetailTabLayout.setupWithViewPager(mBinding.recipesDetailViewpager);

    }
}
