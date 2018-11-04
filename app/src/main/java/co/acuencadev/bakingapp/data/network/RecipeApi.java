package co.acuencadev.bakingapp.data.network;

import java.util.List;

import co.acuencadev.bakingapp.data.models.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

    @GET("/baking.json")
    Call<List<Recipe>> getRecipes();
}
