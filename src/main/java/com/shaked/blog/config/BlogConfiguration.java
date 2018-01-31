package com.shaked.blog.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by USER1 on 1/26/2018.
 */
@Configuration
@EnableArangoRepositories(basePackages = {"com.shaked.blog"})
public class BlogConfiguration extends AbstractArangoConfiguration {

    ArangoConnectConfig arangoConnectConfig;

    @Autowired
    public void setArangoConnectConfig(ArangoConnectConfig arangoConnectConfig) {
        this.arangoConnectConfig = arangoConnectConfig;
    }

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host(arangoConnectConfig.getHost(), arangoConnectConfig.getPort())
                .user(arangoConnectConfig.getUser())
                .password(arangoConnectConfig.getPassword());
    }

    @Override
    public String database() {
        return arangoConnectConfig.getDb();
    }
}
