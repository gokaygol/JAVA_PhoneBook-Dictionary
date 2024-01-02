package MyPhoneDict.DataStructures;

import java.io.Serializable;

import MyPhoneDict.Enums.Gender;
import MyPhoneDict.Enums.PhoneType;

public class ListNode implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Surname;
	private String PhoneNumber;
	private PhoneType PhoneType;
	private Gender Sex;
	private String Email;
	private String Birthday;
	private String address;
	public ListNode next;
	
	public ListNode(String name, String surname, String phoneNumber, PhoneType phoneType, Gender sex, String email, String BirthDay,String address) {
		this.Name = name;
		this.Surname = surname;
		this.PhoneNumber = phoneNumber;
		this.PhoneType = phoneType;
		this.Sex = sex;
		this.Email = email;
		this.Birthday = BirthDay;
		this.address = address;
	}

	public String getName() {
		return Name;
	}

	public String getAddress() {
		return address;
	}


	public void setName(String name) {
		Name = name;
	}



	public String getSurname() {
		return Surname;
	}



	public void setSurname(String surname) {
		Surname = surname;
	}



	public String getPhoneNumber() {
		return PhoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}



	public PhoneType getPhoneType() {
		return PhoneType;
	}



	public void setPhoneType(PhoneType phoneType) {
		PhoneType = phoneType;
	}



	public Gender getSex() {
		return Sex;
	}



	public void setSex(Gender sex) {
		Sex = sex;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getBirthday() {
		return Birthday;
	}



	public void setBirthday(String birthday) {
		Birthday = birthday;
	}


	public ListNode getNext() {
		return next;
	}


	public void setNext(ListNode next) {
		this.next = next;
	}
}