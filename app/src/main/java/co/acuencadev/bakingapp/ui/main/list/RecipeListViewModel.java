package co.acuencadev.bakingapp.ui.main.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import co.acuencadev.bakingapp.data.BakingAppRepository;
import co.acuencadev.bakingapp.data.models.Recipe;

public class RecipeListViewModel extends ViewModel {

    private final BakingAppRepository mRepository;
    private final LiveData<List<Recipe>> mRecipes;

    public RecipeListViewModel(BakingAppRepository repository) {
        this.mRepository = repository;
        this.mRecipes = this.mRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return this.mRecipes;
    }
}
