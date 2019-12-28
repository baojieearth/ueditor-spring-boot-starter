package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.spring.EditorUploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author baojie
 */
public class DefaultUploader implements EditorUploader {

    @Override
    public State binaryUpload(HttpServletRequest request, Map<String, Object> conf) {
        return BinaryUploader.save(request, conf);
    }

    @Override
    public State base64Upload(HttpServletRequest request, Map<String, Object> conf) {
        String filedName = (String) conf.get("fieldName");
        return Base64Uploader.save(request.getParameter(filedName), conf);
    }

    @Override
    public State listImage(int index, Map<String, Object> conf) {
        return new FileManager(conf).listFile(index);
    }

    @Override
    public State listFile(int index, Map<String, Object> conf) {
        return new FileManager(conf).listFile(index);
    }

    @Override
    public State imageHunter(String[] list, Map<String, Object> conf) {
        return new ImageHunter(conf).capture(list);
    }
}
