package com.pyro.mobilepolice.SMSManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.pyro.mobilepolice.beans.Contact;
import com.pyro.mobilepolice.beans.SMS;
import com.pyro.mobilepolice.utils.ExcelWriter;

public class SMSManager {

	public void readAndWriteSMS(Context context) {
		try {
			List<SMS> smsList = fetchSMS(context);
			write(smsList);
		} catch (Exception ex) {

		}
	}

	public void write(List<SMS> smsList) throws IOException,
			WriteException {

		String[] headers = new String[3];
		headers[0] = "Sender";
		headers[1] = "Date";
		headers[2] = "Content";
		File file = ExcelWriter.createFile("sms.xls");
		WritableWorkbook workbook = ExcelWriter
				.createWorkBook(file, "SMS");
		WritableSheet excelSheet = workbook.getSheet(0);
		ExcelWriter.createLabel(excelSheet, headers);
		createContent(excelSheet, smsList);

		workbook.write();
		workbook.close();
	}
	private void createContent(WritableSheet sheet, List<SMS> smsList)
			throws WriteException, RowsExceededException {
		int i = 1;
		for (SMS sms : smsList) {
			String sender = sms.getFrom();
			String date= sms.getDate().toLocaleString();
			String content= sms.getContent();
			ExcelWriter.addLabel(sheet, 0, i, sender);
			ExcelWriter.addLabel(sheet, 1, i, date);
			ExcelWriter.addLabel(sheet, 2, i, content);
			i++;
		}

	}

	private List<SMS> fetchSMS(Context context) {
		List<SMS> smsList = new ArrayList<SMS>();
		Uri uriSMSURI = Uri.parse("content://sms/inbox");
		Cursor cur = context.getContentResolver().query(uriSMSURI, null, null,
				null, null);
		while (cur.moveToNext()) {

			SMS sms = new SMS();
			sms.setFrom(cur.getString(2));

			long milliseconds = Long.parseLong(cur.getString(4));
			Date date = new Date(milliseconds);
			sms.setDate(date);
			sms.setContent(cur.getString(11));
			smsList.add(sms);
		}
		return smsList;
	}
}
