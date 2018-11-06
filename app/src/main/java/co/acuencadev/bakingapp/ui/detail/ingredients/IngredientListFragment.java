package co.acuencadev.bakingapp.ui.detail.ingredients;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.FragmentIngredientListBinding;
import co.acuencadev.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientListFragment extends Fragment {

    private IngredientListViewModel mViewModel;
    private FragmentIngredientListBinding mBinding;

    public IngredientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredient_list,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeIngredientData();
    }

    private void observeIngredientData() {
        IngredientListViewModelFactory factory = InjectorUtils.provideIngredientListViewModelFactory(
                getActivity()
        );
        mViewModel = ViewModelProviders.of(this, factory).get(IngredientListViewModel.class);
    }
}
