package me.kingfl.facecat;

import com.alibaba.druid.pool.DruidDataSource;
import me.kingfl.facecat.service.Tools;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("me.kingfl.facecat.mapper")
public class FacecatApplication {


    public static void main(String[] args) {
        //TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
//        Tools.getLastWeek();
//        Tools.getLastMonth();
        SpringApplication.run(FacecatApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }




}
