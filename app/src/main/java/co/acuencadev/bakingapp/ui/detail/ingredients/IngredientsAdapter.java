package co.acuencadev.bakingapp.ui.detail.ingredients;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.data.models.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private final Context mContext;

    private List<Ingredient> mIngredients;

    public IngredientsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_ingredient,
                viewGroup, false);

        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, int i) {
        ingredientsViewHolder.bind(mIngredients.get(i));
    }

    @Override
    public int getItemCount() {
        return mIngredients == null ? 0 : mIngredients.size();
    }

    public void swapIngredients(final List<Ingredient> ingredients) {
        if (mIngredients == null) {
            mIngredients = ingredients;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mIngredients.size();
                }

                @Override
                public int getNewListSize() {
                    return ingredients.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mIngredients.get(oldItemPosition).getIngredient().equals(
                            ingredients.get(newItemPosition).getIngredient()
                    );
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Ingredient oldIngredient = mIngredients.get(oldItemPosition);
                    Ingredient newIngredient = ingredients.get(newItemPosition);

                    return oldIngredient.getId() == newIngredient.getId()
                            && oldIngredient.getIngredient().equals(newIngredient.getIngredient())
                            && oldIngredient.getQuantity() == newIngredient.getQuantity()
                            && oldIngredient.getMeasure().equals(newIngredient.getMeasure());
                }
            });

            mIngredients = ingredients;
            result.dispatchUpdatesTo(this);
        }
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView quantityTextView;
        TextView measureTextView;

        public IngredientsViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.ingredient_name_textView);
            quantityTextView = itemView.findViewById(R.id.ingredient_quantity_textView);
            measureTextView = itemView.findViewById(R.id.ingredient_measure_textView);
        }

        public void bind(final Ingredient ingredient) {
            nameTextView.setText(ingredient.getIngredient());
            quantityTextView.setText(Double.toString(ingredient.getQuantity()));
            measureTextView.setText(ingredient.getMeasure());
        }
    }
}
