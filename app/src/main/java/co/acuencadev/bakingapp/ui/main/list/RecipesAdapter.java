package co.acuencadev.bakingapp.ui.main.list;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.acuencadev.bakingapp.R;
import co.acuencadev.bakingapp.data.models.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private final Context mContext;

    private List<Recipe> mRecipes;
    private RecipesAdapterListener mListener;

    public RecipesAdapter(Context context, RecipesAdapterListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recipe,
                viewGroup, false);

        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder recipesViewHolder, int i) {
        recipesViewHolder.bind(mRecipes.get(i), mListener);
    }

    @Override
    public int getItemCount() {
        return mRecipes == null ? 0 : mRecipes.size();
    }

    public void swapRecipes(final List<Recipe> recipes) {
        if (mRecipes == null) {
            mRecipes = recipes;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mRecipes.size();
                }

                @Override
                public int getNewListSize() {
                    return recipes.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mRecipes.get(oldItemPosition).getId() == recipes.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Recipe oldRecipe = mRecipes.get(oldItemPosition);
                    Recipe newRecipe = recipes.get(newItemPosition);

                    return oldRecipe.getId() == newRecipe.getId()
                            && oldRecipe.getName().equals(newRecipe.getName())
                            && oldRecipe.getServings() == newRecipe.getServings()
                            && oldRecipe.getImage().equals(newRecipe.getImage());
                }
            });

            mRecipes = recipes;
            result.dispatchUpdatesTo(this);
        }
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView descriptionTextView;
        TextView stepsTextView;
        TextView ingredientsTextView;
        ImageView thumbImageView;

        public RecipesViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.recipe_name_textView);
            descriptionTextView = itemView.findViewById(R.id.recipe_description_textView);
            stepsTextView = itemView.findViewById(R.id.recipe_steps_textView);
            ingredientsTextView = itemView.findViewById(R.id.recipe_ingredients_textView);
            thumbImageView = itemView.findViewById(R.id.recipe_thumb_imageView);
        }

        public void bind(final Recipe recipe, final RecipesAdapterListener listener) {
            nameTextView.setText(recipe.getName());
            descriptionTextView.setText(mContext.getResources().getQuantityString(
                    R.plurals.servings, recipe.getServings(), recipe.getServings()));
            stepsTextView.setText(mContext.getResources().getQuantityString(
                    R.plurals.steps, recipe.getSteps().size(), recipe.getSteps().size()));
            ingredientsTextView.setText(mContext.getResources().getQuantityString(
                    R.plurals.ingredients, recipe.getIngredients().size(), recipe.getIngredients().size()
            ));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRecipeClicked(recipe);
                }
            });
        }
    }

    public interface RecipesAdapterListener {

        void onRecipeClicked(Recipe recipe);
    }
}
