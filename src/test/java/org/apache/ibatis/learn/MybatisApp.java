/*
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.learn;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.learn.person.Person;
import org.apache.ibatis.learn.person.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * src/main/resources/mybatis/mybatis-config.xml
 * 参考文章:
 *
 * 1. 美团mybatis缓存:
 * https://tech.meituan.com/2018/01/19/mybatis-cache.html
 *
 * 2. mybatis缓存机制:
 * https://www.cnblogs.com/wuzhenzhao/p/11103043.html
 *
 * 3. mybatis源码学习:
 * http://www.songshuiyang.com/categories/Mybatis/page/2/
 *
 * 4. 芋道源码
 * http://svip.iocoder.cn/categories/MyBatis/
 *
 * 5. 事务管理
 * https://objcoding.com/2019/03/20/mybatis-sqlsession/
 * http://www.songshuiyang.com/2018/12/18/backend/framework/mybatis/sourceCodeAnalysis/Mybatis%E6%BA%90%E7%A0%81(%E4%BA%8C%E5%8D%81)Spring%20Mybatis%E9%9B%86%E6%88%90%E4%B9%8B%E4%BA%8B%E5%8A%A1%E7%AE%A1%E7%90%86/
 */
public class MybatisApp {
    public static void main(String[] args) throws IOException {

       // 读取配置文件
        String resource = "org/apache/ibatis/learn/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
      // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      // 得到SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        try {
          // 得到Mapper
            PersonMapper mapper = session.getMapper(PersonMapper.class);
            Person person = mapper.selectOne(1);
            System.out.println(person.getName());
        } finally {
            session.close();
        }
    }
}
