package co.acuencadev.bakingapp.ui.detail.ingredients;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.acuencadev.bakingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientListFragment extends Fragment {


    public IngredientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_list, container, false);
    }

}
