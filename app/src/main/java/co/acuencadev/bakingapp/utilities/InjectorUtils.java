package co.acuencadev.bakingapp.utilities;

import android.content.Context;

import co.acuencadev.bakingapp.AppExecutors;
import co.acuencadev.bakingapp.data.BakingAppRepository;
import co.acuencadev.bakingapp.data.network.RecipeNetworkDataSource;

public class InjectorUtils {

    public static BakingAppRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        RecipeNetworkDataSource networkDataSource = RecipeNetworkDataSource.getInstance(context,
                executors);

        return BakingAppRepository.getInstance(networkDataSource, executors);
    }

    public static RecipeNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();

        return RecipeNetworkDataSource.getInstance(context, executors);
    }
}
