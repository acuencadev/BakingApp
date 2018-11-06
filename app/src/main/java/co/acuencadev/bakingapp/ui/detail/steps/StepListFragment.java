package co.acuencadev.bakingapp.ui.detail.steps;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.databinding.FragmentStepListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment {

    private StepsAdapter mAdapter;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new StepsAdapter(getActivity());

        mBinding.stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.stepsRecyclerView.setAdapter(mAdapter);
        mBinding.stepsRecyclerView.setHasFixedSize(true);
    }
}
