package top.dl.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.dl.framework.common.utils.Result;
import top.dl.storage.service.AliyunStorageService;
/**
 * @author: minder
 * @createTime: 2025/04/27 16:19
 * @description:
 **/
@RestController
@RequestMapping("api/file")
@Tag(name = "⽂件上传")
@AllArgsConstructor
public class FileUploadController {
    private final AliyunStorageService storageService;
    @PostMapping("upload")
    @Operation(summary = "上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file)
            throws Exception {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的⽂件");
        }
        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传⽂件
        String url = storageService.upload(file.getBytes(), path);
        return Result.ok(url);
    }
}
