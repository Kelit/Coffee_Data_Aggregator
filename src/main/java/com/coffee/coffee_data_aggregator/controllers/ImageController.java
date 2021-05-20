package com.coffee.coffee_data_aggregator.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.coffee.coffee_data_aggregator.message.ResponseFile;
import com.coffee.coffee_data_aggregator.message.ResponseMessage;
import com.coffee.coffee_data_aggregator.message.ResponseProduct;
import com.coffee.coffee_data_aggregator.model.ImageModel;
import com.coffee.coffee_data_aggregator.repository.ImageRepository;
import com.coffee.coffee_data_aggregator.service.ImageStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController extends AbstractRestController<ImageModel, ImageRepository> {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageStorageService imageStorageService;

    public ImageController(ImageRepository repo) {
        super(repo);
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = imageStorageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId().toString())
//                    .toUriString();

            return new ResponseFile(
                    String.valueOf(dbFile.getId()),
                    dbFile.getName(),
                    dbFile.getType(),
                    dbFile.getPicByte());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uplaodImage(@RequestParam("upload") MultipartFile file) throws Exception {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        String message = "";
//        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//                compressBytes(file.getBytes()));

        imageStorageService.store(file);
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping(path = { "/get/{name}" })
    public @ResponseBody List<ResponseFile> getImage(@PathVariable("name") String name) throws IOException {

        List<ResponseFile> retrievedImage = imageRepository.findByName(name).stream().map(
                dbFile -> new ResponseFile(
                        String.valueOf(dbFile.getId()),
                        dbFile.getName(),
                        dbFile.getType(),
                        dbFile.getPicByte())
        ).collect(Collectors.toList());

//        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//                retrievedImage.get().getPicByte());

        return retrievedImage;
    }

//    @GetMapping("/getbyid/{id}")
//    public Optional<ImageModel> getImageById(@PathVariable("id") Long id) throws Exception{
//
//        final Optional<ImageModel> retrievedImage = imageStorageService.getFile(id);
//
////        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
////                retrievedImage.get().getPicByte());
//
//        return retrievedImage;
//    }

    @GetMapping("/getbyid/{id}")
    public @ResponseBody List<ResponseFile> getImageById(@PathVariable("id") Long id) throws Exception{

        List<ResponseFile> file =imageStorageService.getFile(id).stream().map(
                dbFile -> new ResponseFile(
                        String.valueOf(dbFile.getId()),
                        dbFile.getName(),
                        dbFile.getType(),
                        dbFile.getFile()
                )
        ).collect(Collectors.toList());


        return file;
//        final Optional<ImageModel> retrievedImage = imageStorageService.getFile(id);
//
////        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
////                retrievedImage.get().getPicByte());
//
//        return retrievedImage;
    }



    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }


}
