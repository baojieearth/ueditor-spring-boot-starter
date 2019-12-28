package com.baidu.ueditor.spring;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.upload.DefaultUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author baojie
 */
@Slf4j
@Controller
@EnableConfigurationProperties(EditorProperties.class)
public class EditorController {

    public static EditorProperties editorProperties;
    public static EditorUploader editorUploader;

    @Autowired
    public EditorController(EditorProperties editorProperties, @Lazy EditorUploader editorUploader) {
        EditorController.editorProperties = editorProperties;
        EditorController.editorUploader = editorUploader;
    }

    @RequestMapping({"${ue.server-url}"})
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            String exec = new ActionEnter(request).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Bean
    @ConditionalOnMissingBean(EditorUploader.class)
    public DefaultUploader getDefaultUploader(){
        return new DefaultUploader();
    }

}
