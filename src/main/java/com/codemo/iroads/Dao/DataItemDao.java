package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.DataItem;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dushan on 4/25/18.
 */

@Repository
public interface DataItemDao {

    List<DataItem> getAll();

    List<DataItem> getDataItemByJourneyID(String journeyID);

    List<DataItem> getDummy();



}
