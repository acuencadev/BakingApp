package co.acuencadev.bakingapp.ui.detail.steps;

import android.content.Context;
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
import co.acuencadev.bakingapp.data.models.Step;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final Context mContext;

    private List<Step> mSteps;

    public StepsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_step,
                viewGroup, false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
        stepViewHolder.bind(this.mSteps.get(i));
    }

    @Override
    public int getItemCount() {
        return this.mSteps == null ? 0 : this.mSteps.size();
    }

    public void swapRecipes(final List<Step> steps) {
        if (mSteps == null) {
            mSteps = steps;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mSteps.size();
                }

                @Override
                public int getNewListSize() {
                    return steps.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mSteps.get(oldItemPosition).getShortDescription().equals(
                            steps.get(newItemPosition).getShortDescription()
                    );
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Step oldStep = mSteps.get(oldItemPosition);
                    Step newStep = steps.get(newItemPosition);

                    return oldStep.getId() == newStep.getId()
                            && oldStep.getShortDescription().equals(newStep.getShortDescription())
                            && oldStep.getDescription().equals(newStep.getDescription())
                            && oldStep.getThumbnailURL().equals(newStep.getThumbnailURL())
                            && oldStep.getVideoURL().equals(newStep.getVideoURL()
                    );
                }
            });

            mSteps = steps;
            result.dispatchUpdatesTo(this);
        }
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;
        ImageView thumbnailImageView;

        public StepViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.step_title_textView);
            descriptionTextView = itemView.findViewById(R.id.step_description_textView);
            thumbnailImageView = itemView.findViewById(R.id.step_image_imageView);
        }

        public void bind(final Step step) {
            titleTextView.setText(step.getShortDescription());
            descriptionTextView.setText(step.getDescription());

            //TODO: Display the thumbnail
        }
    }
}
