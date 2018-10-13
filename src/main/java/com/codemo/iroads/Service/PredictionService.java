package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.Prediction;
import com.codemo.iroads.Domain.PredictionGroupWrapper;
import com.couchbase.client.java.document.json.JsonArray;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public interface PredictionService {

    void store(MultipartFile file, String predictionGroup, String subName);

    List<Prediction> getPredictionsByGroupId(String groupId);

    List<Prediction> getAllPredictions();

    List<PredictionGroupWrapper> getPredictionGroups();
}
