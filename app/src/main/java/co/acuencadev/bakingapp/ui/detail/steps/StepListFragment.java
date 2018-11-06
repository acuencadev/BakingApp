package co.acuencadev.bakingapp.ui.detail.steps;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.FragmentStepListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment {

    private FragmentStepListBinding mBinding;


    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_list,
                container, false);
        return mBinding.getRoot();
    }

}
