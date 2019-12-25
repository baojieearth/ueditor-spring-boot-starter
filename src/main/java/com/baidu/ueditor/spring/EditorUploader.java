package com.baidu.ueditor.spring;

import com.baidu.ueditor.define.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface EditorUploader {
    /**
     * 上传文件方法
     *
     * @param request 上传请求
     * @return 文件路径
     */
    State binaryUpload(HttpServletRequest request, Map<String, Object> conf);

    /**
     * Base64上传文件方法 百度编辑器中的涂鸦
     *
     * @param request 上传请求
     * @return 文件路径
     */
    State base64Upload(HttpServletRequest request, Map<String, Object> conf);
}
