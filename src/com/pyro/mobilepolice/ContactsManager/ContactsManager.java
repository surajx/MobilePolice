package com.pyro.mobilepolice.ContactsManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.pyro.mobilepolice.beans.Contact;
import com.pyro.mobilepolice.utils.ExcelWriter;

public class ContactsManager {
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;

	public final static String TAG = "ContactsManager";

	public void readAndWriteContacts(Context context) {
		try {
			List<Contact> contactList = fetchContacts(context);

			write(contactList);
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(List<Contact> contactList) throws IOException,
			WriteException {

		int maxNumbers = 0;
		for (Contact contact : contactList) {
			int size = contact.getPhoneNumbers().size();
			if (size > maxNumbers) {
				maxNumbers = size;
			}
		}
		String[] headers = new String[maxNumbers + 1];
		headers[0] = "name";
		for (int i = 1; i <= maxNumbers; i++) {
			headers[i] = "Phone Number";
		}
		File file = ExcelWriter.createFile("contacts.xls");
		WritableWorkbook workbook = ExcelWriter
				.createWorkBook(file, "Contacts");
		WritableSheet excelSheet = workbook.getSheet(0);
		ExcelWriter.createLabel(excelSheet, headers);
		createContent(excelSheet, contactList);

		workbook.write();
		workbook.close();
	}

	private void createContent(WritableSheet sheet, List<Contact> contactList)
			throws WriteException, RowsExceededException {
		int i = 1;
		for (Contact contact : contactList) {
			String name = contact.getName();
			ExcelWriter.addLabel(sheet, 0, i, name);
			int phoneIndex = 1;
			for (String number : contact.getPhoneNumbers()) {
				ExcelWriter.addLabel(sheet, phoneIndex, i, number);
				phoneIndex++;
			}
			i++;
		}

	}

	public List<Contact> fetchContacts(Context context) {
		List<Contact> contacts = new ArrayList<Contact>();

		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (cursor.moveToNext()) {
			Contact contact = new Contact();
			String name = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			contact.setName(name);
			String phoneNumber = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			contact.getPhoneNumbers().add(phoneNumber);
			String contact_Id = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			Cursor pCur = context.getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
					new String[] { contact_Id }, null);
			while (pCur.moveToNext()) {
				String phoneNo = pCur
						.getString(pCur
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				contact.getPhoneNumbers().add(phoneNo);
			}
			pCur.close();
			contacts.add(contact);

		}
		// Log.d(TAG, contacts.toString());
		return contacts;
	}
}
