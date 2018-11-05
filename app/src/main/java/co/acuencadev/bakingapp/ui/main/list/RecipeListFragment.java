package co.acuencadev.bakingapp.ui.main.list;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {

    private RecipeListViewModel mViewModel;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeRecipeData();
    }

    private void observeRecipeData() {
        RecipeListViewModelFactory factory = InjectorUtils.provideRecipeListViewModelFactory(
                getActivity());
        mViewModel = ViewModelProviders.of(this, factory).get(RecipeListViewModel.class);
    }
}
