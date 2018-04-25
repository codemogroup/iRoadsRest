package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.DataItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dushan on 4/25/18.
 */

@Repository
public interface DataItemDao {
    List<DataItem> getAll();
}
