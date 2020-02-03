package br.com.anodes.apifaceframe.controller;

import br.com.anodes.apifaceframe.entities.Photo;
import br.com.anodes.apifaceframe.repositories.PhotoRepository;
import br.com.anodes.apifaceframe.services.StorageService;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "*")
public class PhotoController {

    @Value("${api.base-url}")
    private String baseUrl;

    @Autowired
    private final StorageService storageService;

    @Autowired
    private PhotoRepository photoRepository;

    private final Tika tika;

    public PhotoController(StorageService storageService, Tika tika) {
        this.storageService = storageService;
        this.tika = tika;
    }


    @PostMapping
    public Photo create(@RequestParam("name") @NotEmpty final String name,
                        @RequestParam("description") @NotEmpty final String description,
                        @RequestParam("image") @NotNull final MultipartFile image) {


        final String key = UUID.randomUUID().toString() + resolveFileExtension(image);
        storageService.store(key, image);

        final String imageUrl = baseUrl + "/photo/files/" + key;
        Photo photo = Photo.builder().description(description).name(name).image(imageUrl).build();

        return photoRepository.save(photo);
    }

    @ResponseBody
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable @NotNull final String filename) {
        Resource file = storageService.loadAsResource(filename);
        String mimeType = tika.detect(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Optional.ofNullable(mimeType).orElse("application/octet-stream"))
                .body(file);
    }

    private String resolveFileExtension(@RequestParam("image") final MultipartFile image) {
        String extension = "";
        if (StringUtils.hasText(image.getContentType())) {
            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            try {
                MimeType mimeType = allTypes.forName(image.getContentType());
                extension = mimeType.getExtension();
            } catch (MimeTypeException ex) {

            }
        }
        return extension;
    }
}
