package co.acuencadev.bakingapp.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import co.acuencadev.bakingapp.AppExecutors;
import co.acuencadev.bakingapp.data.models.Recipe;
import co.acuencadev.bakingapp.data.network.RecipeNetworkDataSource;

public class BakingAppRepository {

    private static final Object LOCK = new Object();
    private static BakingAppRepository sInstance;

    private final AppExecutors mExecutors;
    private final RecipeNetworkDataSource mRecipeNetworkDataSource;

    public synchronized static BakingAppRepository getInstance(RecipeNetworkDataSource recipeNetworkDataSource,
                                                               AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new BakingAppRepository(recipeNetworkDataSource, executors);
                }
            }
        }

        return sInstance;
    }

    private BakingAppRepository(RecipeNetworkDataSource recipeNetworkDataSource,
                                AppExecutors executors) {
        this.mExecutors = executors;
        this.mRecipeNetworkDataSource = recipeNetworkDataSource;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeNetworkDataSource.getRecipes();
    }
}
