package com.zoho.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zoho.dao.SalesDAO;
import com.zoho.utils.CommonUtils;
import com.zoho.utils.FileReaderUtil;

public class SalesListener implements Runnable {
	public void folderListener() throws SQLException, IOException, InterruptedException, ClassNotFoundException {
		System.out.println("Start of the folder listener");
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("item", "Item");
		mapping.put("price_Product", "Price_Product");
		mapping.put("quantity_Sold", "Quantity_Sold");

		final Path path = FileSystems.getDefault().getPath(new CommonUtils().getPath().substring(1));
		System.out.println(path);
		try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			while (true) {
				final WatchKey wk = watchService.take();
				for (WatchEvent<?> event : wk.pollEvents()) {
					final Path fileName = (Path) event.context();
					System.out.println(fileName);
					wk.reset();
					insertSales(fileName, mapping);
				}
			}
		}
	}

	public static void insertSales(Path fileName, Map<String, String> mapping) {
		try {
			String[] name = fileName.getFileName().toString().split("_");
			Statement statement = new SalesDAO().getConnection();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if (name.length == 4) {
				String temp[] = name[3].split("\\.");
				String date = name[1] + "/" + name[2] + "/" + temp[0];
				long total = 0;
				FileReaderUtil fileReaderUtil = new FileReaderUtil();
				if ("csv".equals(temp[1])) {
					total = fileReaderUtil.readDataFromCSVFile(fileName, mapping);
				} else if ("json".equals(temp[1])) {
					total = fileReaderUtil.readDataFromJSONFile(fileName, mapping);
				}
				String sql = "Insert into public.SUPERMARKET_SALESDATA values(" + name[0] + ", '" + format.parse(date).getTime() + "', " + total
						+ ")";
				System.out.println(sql);
				statement.executeUpdate(sql);
				System.out.println("Data updated successfully with area code : " + name[0]);
			}
		} catch (Exception e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			folderListener();
		} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertMockData() throws SQLException {
		long date = new Date().getTime();
		int areacode = 600000;
		int sales = 2000;
		Statement statement = new SalesDAO().getConnection();
		for (int i = 0; i < 1000; i++) {
			String sql = "Insert into public.SUPERMARKET_SALESDATA values(" + areacode + ", '" + date + "', " + sales + ")";
			date -= 86400010;
			areacode += 01;
			sales += 3000;
			statement.addBatch(sql);
		}
		statement.executeBatch();
		
	}

}
