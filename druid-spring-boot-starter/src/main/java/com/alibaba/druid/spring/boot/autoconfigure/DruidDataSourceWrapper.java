/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.spring.boot.autoconfigure;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

/**
 * @author lihengming [89921218@qq.com]
 */
@ConfigurationProperties("spring.datasource.druid")
class DruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
    @Autowired
    private Environment env;

    @Override
    public void afterPropertiesSet() throws Exception {
        //if not found prefix 'spring.datasource.druid' jdbc properties ,'spring.datasource' prefix jdbc properties will be used.
        if (super.getUsername() == null) {
            super.setUsername(env.getProperty("spring.datasource.username"));
        }
        if (super.getPassword() == null) {
            super.setPassword(env.getProperty("spring.datasource.password"));
        }
        if (super.getUrl() == null) {
            super.setUrl(env.getProperty("spring.datasource.url"));
        }
        if (super.getDriverClassName() == null) {
            super.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        }
    }

    @Autowired(required = false)
    public void addStatFilter(StatFilter statFilter) {
        super.filters.add(statFilter);
    }

    @Autowired(required = false)
    public void addConfigFilter(ConfigFilter configFilter) {
        super.filters.add(configFilter);
    }

    @Autowired(required = false)
    public void addEncodingConvertFilter(EncodingConvertFilter encodingConvertFilter) {
        super.filters.add(encodingConvertFilter);
    }

    @Autowired(required = false)
    public void addSlf4jLogFilter(Slf4jLogFilter slf4jLogFilter) {
        super.filters.add(slf4jLogFilter);
    }

    @Autowired(required = false)
    public void addLog4jFilter(Log4jFilter log4jFilter) {
        super.filters.add(log4jFilter);
    }

    @Autowired(required = false)
    public void addLog4j2Filter(Log4j2Filter log4j2Filter) {
        super.filters.add(log4j2Filter);
    }

    @Autowired(required = false)
    public void addCommonsLogFilter(CommonsLogFilter commonsLogFilter) {
        super.filters.add(commonsLogFilter);
    }

    @Autowired(required = false)
    public void addWallFilter(WallFilter wallFilter) {
        super.filters.add(wallFilter);
    }


}
