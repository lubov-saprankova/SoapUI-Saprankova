package ru.geekbrains.mini.market.lesson5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import ru.geekbrains.mini.market.lesson5.api.CategoryService;
import ru.geekbrains.mini.market.lesson5.api.ProductService;
import ru.geekbrains.mini.market.lesson5.dto.GetCategoryResponse;
import ru.geekbrains.mini.market.lesson5.dto.Product;
import ru.geekbrains.mini.market.lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class DeleteProductsTest {

    static CategoryService categoryService;
    Product product = null;

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id) {
        return null;
    }

    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));


    }

}
