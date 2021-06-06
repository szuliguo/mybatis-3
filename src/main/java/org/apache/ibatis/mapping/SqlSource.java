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
package org.apache.ibatis.mapping;

/**
 * MappedStatement 对象的重点属性是SqlSource，本章来介绍SqlSource的构造过程，
 * 其最终执行的sql字符串就是由SqlSource提供的。
 *
 * Mybatis解析sql的时机，Mybatis对于用户在XXMapper.xml文件中配置的sql解析主要分为2个时机
 * 1. 静态sql：程序启动的时候解析
 * 2. 动态sql：用户进行查询等sql相关操作的时候解析
 *
 * 什么是静态sql，动态sql？
 * 1. 如果select|insert|update|delete标签体内包含XML标签或者select|insert|update|delete
 * 标签体内的sql文本中包含${}参数占位符则为动态sql，否则为静态sql。
 * 2.其余为静态sql
 *
 * Represents the content of a mapped statement read from an XML file or an annotation.
 * It creates the SQL that will be passed to the database out of the input parameter received from the user.
 *
 * @author Clinton Begin
 */
public interface SqlSource {

  BoundSql getBoundSql(Object parameterObject);

}
