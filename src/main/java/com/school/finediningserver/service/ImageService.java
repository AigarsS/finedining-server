package com.school.finediningserver.service;

import com.school.finediningserver.model.Image;
import com.school.finediningserver.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(filename, file.getContentType(), file.getBytes());

        return imageRepository.save(image);
    }

}
