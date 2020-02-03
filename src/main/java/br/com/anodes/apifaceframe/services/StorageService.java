package br.com.anodes.apifaceframe.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void store(String key, MultipartFile file);

    Resource loadAsResource(String filename);

    Path load(String filename);

}
