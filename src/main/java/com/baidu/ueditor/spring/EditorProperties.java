package com.baidu.ueditor.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baojie
 */
@Data
@ConfigurationProperties(prefix = "ue")
public class EditorProperties {

    private String serverUrl;

    private Local local;

    private Qiniu qiniu;

    /**
     * 上传到本地参数
     */
    @Data
    public static class Local {
        /**
         * 资源访问前缀
         */
        private String urlPrefix;
        /**
         * 存储文件的绝对路径 必须使用标准路径"/"作为分隔符
         * 默认为"/"即当前项目所在磁盘根目录
         */
        private String physicalPath = "/";
    }

    @Data
    public static class Qiniu {
        private String accessKey;
        private String secretKey;
        private String cdn;
        private String bucket;
        private String zone;
    }

}
