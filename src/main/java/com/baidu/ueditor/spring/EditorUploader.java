package com.baidu.ueditor.spring;

import com.baidu.ueditor.define.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author baojie
 */
public interface EditorUploader {
    /**
     * 上传文件方法
     *
     * @param request 上传请求
     * @param conf
     * @return 文件路径
     */
    State binaryUpload(HttpServletRequest request, Map<String, Object> conf);

    /**
     * Base64上传文件方法 百度编辑器中的涂鸦
     *
     * @param request 上传请求
     * @param conf
     * @return 文件路径
     */
    State base64Upload(HttpServletRequest request, Map<String, Object> conf);

    /**
     * 获取图片列表
     *
     * @param index 位置
     * @param conf
     * @return 文件列表
     */
    State listImage(int index, Map<String, Object> conf);

    /**
     * 获取文件列表
     *
     * @param index 位置
     * @param conf
     * @return 文件列表
     */
    State listFile(int index, Map<String, Object> conf);

    /**
     * 抓取远程图片
     *
     * @param list 图片列表
     * @param conf
     * @return 文件列表
     */
    State imageHunter(String[] list, Map<String, Object> conf);
}
