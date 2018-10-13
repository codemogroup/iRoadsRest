package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.Prediction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionDao {

    void save(Prediction prediction);

    List<Prediction> getPredictionsByGroupId(String groupId);

    List<Prediction> getAllPredictions();

    List<String> getPredictionGroups();
}
