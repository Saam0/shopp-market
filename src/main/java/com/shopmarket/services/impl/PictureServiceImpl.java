package com.shopmarket.services.impl;

import com.shopmarket.models.Picture;
import com.shopmarket.repositories.PictureRepository;
import com.shopmarket.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Value("${static.img.path}")
    private String imagePath;



    @Override
    public Picture save( MultipartFile file) {
        Picture picture = new Picture();

        if (!file.isEmpty()){
            File imageDir = new File(imagePath);
            if (!imageDir.exists()){
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
        return pictureRepository.save(picture);
    }


}
