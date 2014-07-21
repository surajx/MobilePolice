package com.pyrocrypt.mobilepolice.utils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import android.os.Environment;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelWriter {
	private static WritableCellFormat timesBoldUnderline;
	private static WritableCellFormat times;

	public static WritableWorkbook createWorkBook(File file, String sheetName)
			throws IOException {
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet(sheetName, 0);
		return workbook;
	}

	public static File createFile(String fileName) throws IOException {
		File root = Environment.getExternalStorageDirectory();
		File file = new File(root, fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public static void createLabel(WritableSheet sheet, String[] headers)
			throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);

		// create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(true);

		// Write a few headers
		addCaption(sheet, 0, 0, headers[0]);
		for (int i = 1; i < headers.length; i++) {
			addCaption(sheet, i, 0, headers[i]);
		}

	}

	public static void addCaption(WritableSheet sheet, int column, int row,
			String s) throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	public static void addLabel(WritableSheet sheet, int column, int row,
			String s) throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}
}
