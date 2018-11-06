package co.acuencadev.bakingapp.ui.detail.steps;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import co.acuencadev.bakingapp.databinding.FragmentStepListBinding;
import co.acuencadev.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment {

    private static final String RECIPE_ID = "recipeId";

    private int mRecipeId;

    private StepListViewModel mViewModel;
    private StepsAdapter mAdapter;
    private FragmentStepListBinding mBinding;

    public static StepListFragment newInstance(int recipeId) {
        StepListFragment fragment = new StepListFragment();

        Bundle args = new Bundle();
        args.putInt(RECIPE_ID, recipeId);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipeId = getArguments().getInt(RECIPE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_list,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new StepsAdapter(getActivity());

        mBinding.stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.stepsRecyclerView.setAdapter(mAdapter);
        mBinding.stepsRecyclerView.setHasFixedSize(true);

        observeStepData();
    }

    private void observeStepData() {
        StepListViewModelFactory factory = InjectorUtils.provideStepListViewModelFactory(
                getActivity()
        );
        mViewModel = ViewModelProviders.of(this, factory).get(StepListViewModel.class);
        mViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                //TODO: Refactor this as it is really inefficient.
                Recipe recipe = recipes.get(mRecipeId);

                mAdapter.swapRecipes(recipe.getSteps());
            }
        });
    }
}
