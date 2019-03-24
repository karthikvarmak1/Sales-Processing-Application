package com.zoho.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.zoho.beans.SaleItems;
import com.zoho.beans.SaleItemsList;

public class FileReaderUtil {
	public long readDataFromCSVFile(Path fileName, Map<String, String> mapping) {
		long total = 0;
		try {
			HeaderColumnNameTranslateMappingStrategy<SaleItems> strategy = new HeaderColumnNameTranslateMappingStrategy<SaleItems>();
			strategy.setType(SaleItems.class);
			strategy.setColumnMapping(mapping);

			FileReader filereader = new FileReader(FileSystems.getDefault()
					.getPath(new CommonUtils().getPath().substring(1), fileName.toString()).toString());

			CSVReader csvReader = new CSVReader(filereader);
			CsvToBean<SaleItems> csvToBean = new CsvToBean<SaleItems>();

			@SuppressWarnings("deprecation")
			List<SaleItems> list = csvToBean.parse(strategy, csvReader);

			for (SaleItems s : list) {
				// System.out.println(s);
				total += s.getPrice_Product() * s.getQuantity_Sold();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public long readDataFromJSONFile(Path fileName, Map<String, String> mapping) throws IOException {
		long total = 0;
		System.out.println(FileSystems.getDefault()
				.getPath(new CommonUtils().getPath().substring(1), fileName.toString()).toString());
		BufferedReader br = new BufferedReader(new FileReader(FileSystems.getDefault()
				.getPath(new CommonUtils().getPath().substring(1), fileName.toString()).toString()));
		SaleItemsList saleItems = new Gson().fromJson(br, SaleItemsList.class);

		for (SaleItems s : saleItems.getSaleItems()) {
			// System.out.println(s);
			total += s.getPrice_Product() * s.getQuantity_Sold();
		}

		return total;
	}

}
