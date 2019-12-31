package config;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.h2.tools.Server;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Named
@ApplicationScoped
@Startup
@Singleton
public class DataBaseInitializerBean {

    private final static Logger logger = Logger.getLogger(DataBaseInitializerBean.class);
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:test","sa","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void initializerDB() {
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:test","sa","");
            List<String> sqls = FileUtils.readLines(new File( getClass().getClassLoader().getResource("mock_data.sql").getFile()));
            sqls.stream()
                    .forEach( sql -> {
                        try {
                            conn.prepareStatement(sql).execute();
                            logger.info(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            Server server = Server.createWebServer("-webAllowOthers", "-browser", "-ifNotExists", "-web", "-webPort", "8082");
            logger.info("H2 CONSOLE : http://localhost:8082");
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("--------READY-----------");
    }


    public Connection getConnection() {
        return conn;
    }


}
