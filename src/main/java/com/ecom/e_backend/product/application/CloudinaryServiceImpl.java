package com.ecom.e_backend.product.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecom.e_backend.product.domain.service.CloudinaryService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Value("${cloudinary.cloudName}")
    private String name;
    @Value("${cloudinary.apiKey}")
    private String key;
    @Value("${cloudinary.apiSecret}")
    private String secret;

    public CloudinaryServiceImpl() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", name);
        valuesMap.put("api_key", key);
        valuesMap.put("api_secret", secret);
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Mono<Map> save(MultipartFile multipartFile) {
        return Mono.fromCallable(() -> {
            File file = convert(multipartFile);
            return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        });
    }

    @Override
    public Mono<Map> delete(String publicId) {
        return Mono.fromCallable(() -> cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap()));
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

}
