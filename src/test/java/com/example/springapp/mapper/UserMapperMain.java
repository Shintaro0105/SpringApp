package com.example.springapp.mapper;

import com.example.springapp.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class UserMapperMain {
    public static void main(String[] args) throws Exception {
        // MyBatis の設定ファイルを読み込む
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // セッション開始
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 実際にDBからユーザーを取得
            User user = mapper.findByUsername("testuser");

            if (user != null) {
                System.out.println("ユーザーが見つかりました:");
                System.out.println("ID       : " + user.getId());
                System.out.println("Username : " + user.getUsername());
                System.out.println("Role     : " + user.getRole());
            } else {
                System.out.println("ユーザーが見つかりませんでした。");
            }
        }
    }
}
