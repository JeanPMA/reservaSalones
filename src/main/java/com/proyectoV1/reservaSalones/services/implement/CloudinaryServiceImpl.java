package com.proyectoV1.reservaSalones.services.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl {
    Cloudinary cloudinary;
    private Map<String, String> valuesMap = new HashMap<>();
    public CloudinaryServiceImpl(){
        valuesMap.put("cloud_name", "dg2z9wbyn");
        valuesMap.put("api_key", "571269388211769");
        valuesMap.put("api_secret", "OcHDCQaHwDinLm3tHCzH6hTBdT0");
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException{
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }
    public Map delete(String id) throws IOException{
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return  result;
    }

    private File convert(MultipartFile multipartFile) throws IOException{
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(multipartFile.getBytes());
        fileOut.close();
        return file;
    }
}
