package co.acuencadev.bakingapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import co.acuencadev.bakingapp.AppExecutors;
import co.acuencadev.bakingapp.data.models.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeNetworkDataSource {

    private static final Object LOCK = new Object();
    private static RecipeNetworkDataSource sInstance;

    private final Context mContext;
    private final AppExecutors mExecutors;
    private final RecipeApi mAPI;

    private final String API_URL = "https://api.themoviedb.org/3/";

    public synchronized static RecipeNetworkDataSource getInstance(Context context,
                                                                   AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new RecipeNetworkDataSource(context, executors);
                }
            }
        }

        return sInstance;
    }

    private RecipeNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPI = retrofit.create(RecipeApi.class);
    }

    public LiveData<List<Recipe>> getRecipes() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

        mAPI.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

        return data;
    }
}
