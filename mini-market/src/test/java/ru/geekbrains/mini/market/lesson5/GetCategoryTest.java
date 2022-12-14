package ru.geekbrains.mini.market.lesson5;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class GetCategoryTest {


    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        SqlSession session = null;
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new
                    SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();


            db.dao.CategoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
            ru.geekbrains.mini.market.lesson6.db.model.CategoriesExample example = new db.model.CategoriesExample();


            example.createCriteria().andIdEqualTo(1);
            int categories = 0;
            categories.setTitle("test");
            StringBuffer categoriesMapper = new StringBuffer();
            categoriesMapper.insert(categories);
            session.commit();


        } finally {
            session.close();
        }
    }

