package com.manage.demo.util;

import com.manage.demo.pojo.Goods;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReadCsv {
    public static List readCSVFile(String path) throws UnsupportedEncodingException, FileNotFoundException {
        CSVReader reader = null;
            reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8.name())).build();
            HeaderColumnNameMappingStrategy<Goods> mapper = new HeaderColumnNameMappingStrategy<>();
            mapper.setType(Goods.class);
            CsvToBean<Goods> csvInfos = new CsvToBean<>();
            List<Goods> list = csvInfos.parse(mapper,reader);

        return list;
    }

}
