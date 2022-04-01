package com.shopmarket.services;

import com.shopmarket.models.Picture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PictureService {

    Picture save(MultipartFile file);
}
