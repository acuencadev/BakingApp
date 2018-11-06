package co.acuencadev.bakingapp.ui.detail.ingredients;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.FragmentIngredientListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientListFragment extends Fragment {

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

}
