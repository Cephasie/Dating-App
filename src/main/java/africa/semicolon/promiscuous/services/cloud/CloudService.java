package africa.semicolon.promiscuous.services.cloud;

import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile file);
}
