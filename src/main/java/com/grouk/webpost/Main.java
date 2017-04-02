package com.grouk.webpost;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.grouk.webpost.controler.TrackController;
import com.grouk.webpost.util.SceneLoader;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class
 * Created by Alena_Grouk on 3/29/2017.
 */
@Configuration
@ComponentScan("com.grouk.webpost")
@PropertySource("classpath:application.properties")
public class Main extends Application {
    private Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.driver}")
    private String driver;

    private static ApplicationContext appContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOGGER.info("Start application.");

        appContext = new AnnotationConfigApplicationContext(Main.class);
        String name = appContext.getEnvironment().getProperty("application.name");
        String version = appContext.getEnvironment().getProperty("application.version");
        LOGGER.info(String.format("%s %s started", name, version));

        TrackController trackController = appContext.getBean(TrackController.class);
        SceneLoader.loadScene(primaryStage, trackController);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dbUrl);
        return dataSource;
    }
}