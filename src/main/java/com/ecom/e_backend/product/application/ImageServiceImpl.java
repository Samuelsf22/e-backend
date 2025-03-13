package com.ecom.e_backend.product.application;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.e_backend.product.domain.models.Image;
import com.ecom.e_backend.product.domain.repository.ImageRepository;
import com.ecom.e_backend.product.domain.service.CloudinaryService;
import com.ecom.e_backend.product.domain.service.ImageService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    @Override
    public Mono<Image> save(MultipartFile file) {
        Mono<Map> uploadResult = cloudinaryService.save(file);
        String imageUrl = uploadResult.map(map -> map.get("url").toString()).block();
        String publicId = uploadResult.map(map -> map.get("public_id").toString()).block();

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .imageUrl(imageUrl)
                .publicId(publicId)
                .build();

        return imageRepository.save(image);
    }

    @Override
    public Mono<Void> delete(String publicId) {
        return cloudinaryService.delete(publicId)
                .then(imageRepository.delete(publicId));
    }

}
