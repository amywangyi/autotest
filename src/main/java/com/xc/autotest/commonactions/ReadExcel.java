package com.xc.autotest.commonactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {
	public Map<Integer, ArrayList<String>> ParseXls(String filepath, String sheet) {
		Map<Integer, ArrayList<String>> UserCase = new HashMap<Integer, ArrayList<String>>();

		jxl.Workbook readwb = null;
		ArrayList<String> cols = null;
		try {
			InputStream instream = new FileInputStream(filepath);
			readwb = Workbook.getWorkbook(instream);
			Sheet readsheet = readwb.getSheet(sheet);
			int rsColumns = readsheet.getColumns();
			int rsRows = readsheet.getRows();
			for (int i = 0; i < rsRows; i++) {
				cols = new ArrayList<String>();
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = readsheet.getCell(j, i);
					cols.add(cell.getContents().trim());
				}
				UserCase.put(i, cols);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
		return UserCase;
	}

	public Object[][] getUserCaseFromXls(String FilePath, String sheetName) {
		Properties propSystem = System.getProperties();
		String osName = propSystem.getProperty("os.name");
		if (osName.toUpperCase().contains("LINUX")) {
			File directory = new File(".");
			try {
				String currentFilePath = directory.getCanonicalPath();
				currentFilePath = currentFilePath.substring(0, currentFilePath.length() - 15);

				FilePath = FilePath.substring(1);
				FilePath = currentFilePath + FilePath;

				CommonMethods.printLog(FilePath, "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Map<Integer, ArrayList<String>> Cases = ParseXls(FilePath, sheetName);
		ArrayList<Map<String, String>> usercase = ParseCase(Cases);
		Object usercases[][] = new Object[usercase.size()][];
		for (int i = 0; i < usercase.size(); ++i) {
			Map<String, String> mp = usercase.get(i);
			usercases[i] = new Object[] { mp };
		}
		return usercases;
	}

	public Iterator<Object[]> getUserCaseFromXlsByIterator(String FilePath, String sheetName) {
		Map<Integer, ArrayList<String>> Cases = ParseXls(FilePath, sheetName);
		ArrayList<Map<String, String>> usercase = ParseCase(Cases);
		ArrayList<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (Map<String, String> mp : usercase) {
			dataToBeReturned.add(new Object[] { mp });
		}
		return dataToBeReturned.iterator();
	}

	public ArrayList<Map<String, String>> ParseCase(Map<Integer, ArrayList<String>> Cases) {
		ArrayList<String> col = null;
		Map<String, String> Case = null;
		ArrayList<Map<String, String>> usercase = new ArrayList<Map<String, String>>();
		ArrayList<String> header = new ArrayList<String>();
		header = (ArrayList<String>) Cases.get(0);
		for (int j = 1; j < Cases.size(); ++j) {
			col = (ArrayList<String>) Cases.get(j);
			Case = new HashMap<String, String>();
			for (int k = 0; k < col.size(); ++k) {
				Case.put(header.get(k), col.get(k));
			}
			usercase.add(Case);
		}
		return usercase;
	}
}
