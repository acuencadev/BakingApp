package co.acuencadev.bakingapp.ui.detail.ingredients;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import co.acuencadev.bakingapp.data.BakingAppRepository;
import co.acuencadev.bakingapp.data.models.Ingredient;

public class IngredientListViewModel extends ViewModel {

    private final BakingAppRepository mRepository;
    private final LiveData<List<Ingredient>> mIngredients;

    public IngredientListViewModel(BakingAppRepository repository) {
        this.mRepository = repository;
        this.mIngredients = new MutableLiveData<>();
    }

    public LiveData<List<Ingredient>> getIngredients() {
        return this.mIngredients;
    }
}
