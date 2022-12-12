package ru.geekbrains.mini.market.lesson5.api;

import ru.geekbrains.mini.market.lesson5.dto.GetCategoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface CategoryService {

    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
