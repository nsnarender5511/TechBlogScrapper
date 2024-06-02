package org.narender.GraphDB;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;

@Slf4j
public class Neo4jConfig {

    private static SessionFactory instance;
    private static Configuration sessoinFactoryConfig;


    private static final String dbUri = "neo4j+s://d23347cf.databases.neo4j.io";
    private static final String dbUser = "neo4j";
    private static final String dbPassword = "ZDgGmccSfMqtEVyLFv6jJyJcCQjiqbw_AygiAsloKL0";



    static{
        sessoinFactoryConfig = new Configuration.Builder()
                                    .uri(dbUri)
                                    .credentials(dbUser, dbPassword)
                                    .build();
    }


    public static SessionFactory getSessonFactory(){
        if(instance!=null){
            return instance;
        }
        createInstance();
        return getSessonFactory();
    }

    private static void createInstance(){
        log.info("creating session");
        instance = new SessionFactory(sessoinFactoryConfig, "org.narender.GraphDB.models");
    }


}
