package me.kingfl.facecat.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {
    //private String imageSavePath = "../../../../resources/static/images/";
    private String imageSavePath = "D:/images/";
    public static final String ROOT = "upload-dir";
    private ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }



    @ResponseBody
    @RequestMapping(value = "imageupload", method = RequestMethod.POST)
    public Map imageupload(@Param("imageclass") String imageclass,
                           @Param("name") String name,
                           @Param("file") MultipartFile file) {
        System.out.println("imageuploadcmd");
        Map map = null;
        try {
            map = new HashMap();
            String fileName = file.getOriginalFilename();
            String[] fileNameSplit = fileName.split("\\.");
            System.err.println(fileNameSplit[fileNameSplit.length - 1]);
            System.err.println(file.getContentType());
            System.err.println(fileName);
            String filePath = imageSavePath + imageclass + "/";
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
            map.put("isinsert", true);
            map.put("filename", fileName);
            map.put("filepath", filePath);
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
            map.put("isinsert", false);
        }
        return map;
    }


}
