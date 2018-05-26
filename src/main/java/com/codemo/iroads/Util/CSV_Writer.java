package com.codemo.iroads.Util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class CSV_Writer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVWriter.class);

    public static void writeToCsv(PrintWriter writer,List dataList) {
        try  {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(dataList);

        }catch (CsvException ex) {
            LOGGER.error("Error mapping Bean to CSV", ex);

        }
    }

}
