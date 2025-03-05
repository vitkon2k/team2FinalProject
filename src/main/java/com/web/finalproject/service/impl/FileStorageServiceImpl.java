package com.web.finalproject.service.impl;

import com.web.finalproject.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục lưu trữ!", e);
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                init();
            }

            // Tạo tên file duy nhất
            String fileExtension = getFileExtension(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            Path destination = root.resolve(uniqueFileName);

            Files.copy(file.getInputStream(), destination);

            return "uploads/" + uniqueFileName;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage(), e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Không thể đọc file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi khi tải file: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path file = root.resolve(filename);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi xóa file: " + e.getMessage(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Không thể tải danh sách file!", e);
        }
    }

    public String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}
