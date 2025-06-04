package top.dl.storage.service;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.framework.common.exception.ServerException;
import top.dl.storage.config.AliyunOssConfig;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:08
 * @description:
 **/
@Slf4j
@Service
@AllArgsConstructor
public class AliyunStorageService extends StorageService {
    private final AliyunOssConfig aliyunOssConfig;
    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }
    @Override
    public String upload(InputStream inputStream, String path) {
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        String endpoint = aliyunOssConfig.getEndPoint();
        String bucketName = aliyunOssConfig.getBucketName();
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            client.putObject(bucketName, path, inputStream, meta);
        } catch (Exception e) {
            throw new ServerException("上传⽂件失败：", e);
        } finally {
            if (client != null) {

                client.shutdown();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/" + path;
    }
}
