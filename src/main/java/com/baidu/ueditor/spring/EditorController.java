package com.baidu.ueditor.spring;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.Base64Uploader;
import com.baidu.ueditor.upload.BinaryUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author baojie
 */
@Controller
@EnableConfigurationProperties(EditorProperties.class)
public class EditorController {

    public static EditorProperties editorProperties;

    @Autowired
    public EditorController(EditorProperties editorProperties){
        EditorController.editorProperties = editorProperties;
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
            e.printStackTrace();
        }
    }

    @Service
    @ConditionalOnMissingBean(EditorUploader.class)
    class DefaultEditorConfig implements WebMvcConfigurer {

        @Component
        class DefaultEditorUploader implements EditorUploader {

            @Override
            public State binaryUpload(HttpServletRequest request, Map<String, Object> conf) {
                return BinaryUploader.save(request, conf);
            }

            @Override
            public State base64Upload(HttpServletRequest request, Map<String, Object> conf) {
                String filedName = (String) conf.get("fieldName");
                return Base64Uploader.save(request.getParameter(filedName), conf);
            }

        }
    }

}
