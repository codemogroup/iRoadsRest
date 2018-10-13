package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.Prediction;
import com.codemo.iroads.Domain.PredictionGroupWrapper;
import com.codemo.iroads.Repository.NonEntityClassRepository;
import com.codemo.iroads.Repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictionDaoImpl implements PredictionDao {

    @Autowired
    PredictionRepository predictionRepository;

    @Autowired
    NonEntityClassRepository nonEntityClassRepository;

    public void save(Prediction prediction){
        predictionRepository.save(prediction);
    }

    public List<Prediction> getPredictionsByGroupId(String groupId){
        List<Prediction> predictionsByGroupId = predictionRepository.getPredictionsByGroupId(groupId);
        return predictionsByGroupId;
    }

    @Override
    public List<Prediction> getAllPredictions() {
        List<Prediction> predictions = predictionRepository.getAllPredictions();
        return predictions;
    }

    @Override
    public List<PredictionGroupWrapper> getPredictionGroups() {
        List<PredictionGroupWrapper> predictionGroups = nonEntityClassRepository.getPredictionGroups();
        return predictionGroups;
    }
}
