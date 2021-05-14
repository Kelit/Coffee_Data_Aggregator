package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ImageModel;
import com.coffee.coffee_data_aggregator.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepo;

    public ImageModel store(MultipartFile img) throws Exception{

        String fileName = StringUtils.cleanPath(img.getOriginalFilename());
        ImageModel fileImg = new ImageModel(fileName, img.getContentType(), img.getBytes());

        return imageRepo.save(fileImg);
    }

    public Optional<ImageModel> getFile(Long id) {
        return imageRepo.findById(id);
    }

    public Stream<ImageModel> getAllFiles() {
        return imageRepo.findAll().stream();
    }
}
