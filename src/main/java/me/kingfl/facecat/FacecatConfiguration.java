package me.kingfl.facecat;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class FacecatConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**").addResourceLocations("file:D://images/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }




    //    @Value("${res.imagepath}")
//    private String projectimagepath;

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if(projectimagepath.equals("") || projectimagepath.equals("${res.imagepath}")){
//            String imagesPath = FacecatConfiguration.class.getClassLoader().getResource("").getPath();
//            if(imagesPath.indexOf(".jar")>0){
//                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
//            }else if(imagesPath.indexOf("classes")>0){
//                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
//            }
//            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";
//            projectimagepath = imagesPath;
//        }
//        LoggerFactory.getLogger(FacecatConfiguration.class).info("imagesPath="+projectimagepath);
//        registry.addResourceHandler("/images/**").addResourceLocations(projectimagepath);
//        super.addResourceHandlers(registry);
//    }

}
