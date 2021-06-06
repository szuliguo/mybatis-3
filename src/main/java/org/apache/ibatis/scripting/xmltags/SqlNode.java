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

/**
 * SqlNode 是 Mybatis 实现动态sql的核心
 * SqlNode 是 Mybatis 实现动态sql的核心，各种动态标签是通过不同的SqlNode子类来实现的，
 * 可以看到Mybatis会将xml的sql语句封装为一个个Node节点，不同的节点有不同的处理方式
 * @author Clinton Begin
 */
public interface SqlNode {
  boolean apply(DynamicContext context);
}
