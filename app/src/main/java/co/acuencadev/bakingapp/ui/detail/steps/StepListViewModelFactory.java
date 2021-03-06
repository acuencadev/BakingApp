package co.acuencadev.bakingapp.ui.detail.steps;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import co.acuencadev.bakingapp.data.BakingAppRepository;

public class StepListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final BakingAppRepository mRepository;

    public StepListViewModelFactory(BakingAppRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StepListViewModel(this.mRepository);
    }
}
