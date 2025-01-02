package com.rafaelhosaka.rhv.user.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CloudinaryService {
    @Autowired
    private Environment environment;

    private Cloudinary getCloudinary(){
        return new Cloudinary(environment.getProperty("CLOUDINARY_URL"));
    }

    public String upload (byte[] fileBytes, String folderName, String fileName, String type) throws IOException {
        var cloudinary = getCloudinary();
        var map = ObjectUtils.asMap(
                "folder", "rhv/"+folderName,
                "resource_type", type,
                "public_id", fileName
        );
        var result = cloudinary.uploader().upload(fileBytes, map);
        return (String) result.get("url");
    }
}
