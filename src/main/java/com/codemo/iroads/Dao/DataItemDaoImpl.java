package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Repository.DataItemRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dushan on 4/25/18.
 */

@Component
public class DataItemDaoImpl implements DataItemDao {

    @Autowired
    DataItemRepository dataItemRepository;

    @Override
    public List<DataItem> getAll() {
        return dataItemRepository.getAll();
    }

    @Override
    public List<DataItem> getDataItemByJourneyID(String journeyID) {
        List<DataItem> dataItemByJourneyID = dataItemRepository.getDataItemByJourneyID(journeyID);
        return dataItemByJourneyID;
    }

    @Autowired
    Gson gson;

    public List<DataItem> getDummy(){
        ClassLoader classLoader = getClass().getClassLoader();
        String filename="journey3599880608007481537609916152.json";

        InputStream is = classLoader.getResourceAsStream(filename);
        String jsonText = null;
        try {
            jsonText = IOUtils.toString(is,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<DataItem>  dataItems = gson.fromJson(jsonText, new TypeToken<List<DataItem>>(){}.getType());
        return dataItems;
    }

}