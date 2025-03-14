package com.ecom.e_backend.product.infrastructure.cloudinary;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecom.e_backend.product.domain.service.CloudStorageService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudStorageService {

    private Cloudinary cloudinary;

    @Value("${cloudinary.cloudName}")
    private String name;
    @Value("${cloudinary.apiKey}")
    private String key;
    @Value("${cloudinary.apiSecret}")
    private String secret;
    
    @PostConstruct
    public void init() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", name);
        valuesMap.put("api_key", key);
        valuesMap.put("api_secret", secret);
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Mono<Map> save(FilePart filePart) {
        return Mono.fromCallable(() -> {
            File tempFile = File.createTempFile("upload-", filePart.filename());
            return tempFile;
        }).flatMap(tempFile -> 
            filePart.transferTo(tempFile.toPath())
                .then(Mono.fromCallable(() -> {
                    Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
                    tempFile.delete();
                    return uploadResult;
                }))
        );
    }

    @Override
    public Mono<Map> delete(String publicId) {
        return Mono.fromCallable(() -> cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap()));
    }
}
