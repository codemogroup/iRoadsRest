package com.codemo.iroads.Rest;


import com.codemo.iroads.Service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @Autowired
    PredictionService predictionService;



    @PostMapping("/uploadPredicted")
    public ResponseEntity< String > handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("predictionGroup") String predictionGroup,
            @RequestParam("subName") String subName
            ) {
        System.out.println("Post came");

        String message = "";
        try {

            predictionService.store(file,predictionGroup,subName);

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);

        } catch (Exception e) {
            message = "Fail to upload Profile Picture" + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

}
