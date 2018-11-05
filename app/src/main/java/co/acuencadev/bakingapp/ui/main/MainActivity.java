package co.acuencadev.bakingapp.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.ActivityMainBinding;
import co.acuencadev.bakingapp.ui.main.list.RecipeListFragment;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "co.acuencadev.bakingapp.params.RecipeId";

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loadFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        RecipeListFragment fragment = new RecipeListFragment();

        fragmentManager.beginTransaction()
                .add(R.id.list_container, fragment)
                .commit();
    }
}
