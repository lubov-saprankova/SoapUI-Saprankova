package ru.geekbrains.mini.market.lesson6;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.mini.market.lesson5.api.CategoryService;
import ru.geekbrains.mini.market.lesson5.dto.GetCategoryResponse;
import ru.geekbrains.mini.market.lesson5.utils.RetrofitUtils;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {
    SqlSession session = null;
        try {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();


        db.dao.CategoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
        ru.geekbrains.mini.market.lesson6.db.model.CategoriesExample example = new db.model.CategoriesExample();


        example.createCriteria().andIdEqualTo(1);
        int categories = 0;
        categories.setTitle("test");
        StringBuilder categoriesMapper = new StringBuilder();
        categoriesMapper.insert(categories);
        session.commit();


    } finally {
        session.close();
    }
}