package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.DataItemDao;
import com.codemo.iroads.Domain.DataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dushan on 4/25/18.
 */

@Service
public class DataItemServiceImpl implements DataItemService {

    @Autowired
    DataItemDao dataItemDao;

    @Override
    public List<DataItem> getAll() {
        List<DataItem> dataItems=(List<DataItem> ) dataItemDao.getAll();
        return dataItems;
    }
}
