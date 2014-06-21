package com.pyro.mobilepolice.beans;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	private String name;
	private List<String> phoneNumbers;

	public Contact() {
		phoneNumbers = new ArrayList<String>();
	}

	@Override
	public String toString() {

		return "Name: " + getName() + "  Numbers: " + phoneNumbers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

}
