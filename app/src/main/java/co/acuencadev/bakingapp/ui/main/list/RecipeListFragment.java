package co.acuencadev.bakingapp.ui.main.list;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.data.models.Recipe;
import co.acuencadev.bakingapp.databinding.FragmentRecipeListBinding;
import co.acuencadev.bakingapp.ui.detail.DetailActivity;
import co.acuencadev.bakingapp.ui.main.MainActivity;
import co.acuencadev.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment implements RecipesAdapter.RecipesAdapterListener {

    private RecipeListViewModel mViewModel;
    private RecipesAdapter mAdapter;
    private FragmentRecipeListBinding mBinding;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new RecipesAdapter(getActivity(), this);

        mBinding.recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recipesRecyclerView.setAdapter(mAdapter);
        mBinding.recipesRecyclerView.setHasFixedSize(true);

        observeRecipeData();
    }

    private void observeRecipeData() {
        RecipeListViewModelFactory factory = InjectorUtils.provideRecipeListViewModelFactory(
                getActivity());
        mViewModel = ViewModelProviders.of(this, factory).get(RecipeListViewModel.class);
        mViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mAdapter.swapRecipes(recipes);
            }
        });
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);

        intent.putExtra(MainActivity.EXTRA_RECIPE_ID, recipe.getId());

        startActivity(intent);
    }
}
