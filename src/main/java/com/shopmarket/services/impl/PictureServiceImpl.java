package com.shopmarket.services.impl;

import com.shopmarket.exceptions.NoSuchFileException;
import com.shopmarket.models.Picture;
import com.shopmarket.models.Product;
import com.shopmarket.repositories.PictureRepository;
import com.shopmarket.services.PictureService;
import com.shopmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ProductService productService;

    @Value("${static.img.path}")
    private String imagePath;


    @Override
    public Picture save(MultipartFile file) {
        Picture picture = new Picture();

        if (!file.isEmpty()) {
            createFileAndFileName(file, picture);
        }
        return pictureRepository.save(picture);
    }

    @Override
    public Picture findByProduct(Product product) {
        return pictureRepository.findByProduct(product);
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }


    @Override
    public Picture update(MultipartFile file, Long productId) {
        Picture picture = null;
        if (productId != null) {

            picture = findById(productService.findById(productId).get().getPicture().getId()).get();

                try {
                    Files.deleteIfExists(Paths.get(imagePath + "/" + picture.getSmPictureName()));
                } catch (IOException e) {
                    throw new NoSuchFileException("There is no such file named gago " + picture.getSmPictureName());
                }

        } else {
            picture = new Picture();
        }
        if (!file.isEmpty()) {
            createFileAndFileName(file, picture);
        }
        return pictureRepository.save(picture);
    }

    private void createFileAndFileName(MultipartFile file, Picture picture) {
        File imageDir = new File(imagePath);
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "-" + file.getOriginalFilename();
        try {
            file.transferTo(new File(imagePath + "/" + resultFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture.setSmPictureName(resultFileName);
    }


}
