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
package org.apache.ibatis.scripting.xmltags;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 处理动态sql语句。
 * DynamicSqlSource和StaticSqlSource的最大区别在于：
 * StaticSqlSource的String sql，可以直接获取使用，
 * 而DynamicSqlSource的String sql需要逐一根据条件解析并拼接出最终的sql方能使用。
 * @author Clinton Begin
 */
public class DynamicSqlSource implements SqlSource {

  private final Configuration configuration;
  private final SqlNode rootSqlNode;

  public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
    this.configuration = configuration;
    this.rootSqlNode = rootSqlNode;
  }

  /**
   * 得到绑定的SQL
   */
  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    // 生成一个动态上下文
    DynamicContext context = new DynamicContext(configuration, parameterObject);
    // 这里SqlNode.apply只是将${}这种参数替换掉，并没有替换#{}这种参数
    rootSqlNode.apply(context);
    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
    Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
    // SqlSourceBuilder.parse,注意这里返回的是StaticSqlSource,
    //解析完了就把那些#{password}参数都替换成?了，也就是最基本的JDBC的SQL写法
    SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
    // 看似是又去递归调用SqlSource.getBoundSql，
    //其实因为是StaticSqlSource，所以没问题，不是递归调用
    BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
    context.getBindings().forEach(boundSql::setAdditionalParameter);
    return boundSql;
  }

}
