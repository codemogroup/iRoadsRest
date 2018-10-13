package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.PredictionDao;
import com.codemo.iroads.Domain.Prediction;
import com.codemo.iroads.Domain.PredictionGroupWrapper;
import com.couchbase.client.java.document.json.JsonArray;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    PredictionDao predictionDao;


    @Autowired
    Gson gson;

    public void store(MultipartFile file, String predictionGroup, String subName) {
        try {

            InputStream inputStream = file.getInputStream();

            String str = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

//            JsonArray predictionData=   JsonArray.fromJson(str);

            ArrayList predictionData = gson.fromJson(str, new ArrayList<Prediction>().getClass());

            Prediction prediction = new Prediction(predictionGroup, subName, predictionData);

            predictionDao.save(prediction);

            System.out.println("saved..prediction");

        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public List<Prediction> getPredictionsByGroupId(String groupId) {
        List<Prediction> predictionsByGroupId = predictionDao.getPredictionsByGroupId(groupId);
        return predictionsByGroupId;
    }

    @Override
    public List<Prediction> getAllPredictions() {
        List<Prediction> predictions = predictionDao.getAllPredictions();
        return predictions;
    }

    @Override
    public List<PredictionGroupWrapper> getPredictionGroups() {
        List<PredictionGroupWrapper> predictionGroups = predictionDao.getPredictionGroups();

        return predictionGroups;
    }
}
