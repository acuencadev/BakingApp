package co.acuencadev.bakingapp.data.network;

import java.util.List;

import co.acuencadev.bakingapp.data.models.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeApi {

    @GET("{file}")
    Call<List<Recipe>> getRecipes(@Path("file") String file);
}
