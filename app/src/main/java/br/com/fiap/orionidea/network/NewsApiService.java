package br.com.fiap.orionidea.network;

import br.com.fiap.orionidea.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("v2/top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("apiKey") String apiKey,
            @Query("country") String country,
            @Query("category") String category

    );
}
