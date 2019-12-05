package com.vchmgi.myappointment;

/**
 * Created by Naresh on 19-01-2017.
 */

public class RowItem {
    String date;
    private final String name;
    private final String email;
    private final String phone;
    private final String sub;
    private final String msg;

    public RowItem(String date, String name, String email, String phone, String sub, String msg){
        this.date = date;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sub = sub;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSub() {
        return sub;
    }

    public String getMsg() {
        return msg;
    }

    public String getDate() {return date;}
    /*
String fullNameId;
    String emailId;
    String phoneNoId;
    String subId;
    String messageId;


    public RowItem(String fullNameId,String emailId,String phoneNoId,String subId,String messageId){
        this.setFullNameId(fullNameId);
        this.setEmailId(emailId);
        this.setPhoneNoId(phoneNoId);
        this.setSubId(subId);
        this.setMessageId(messageId);
    }



    public String getFullNameId() {
        return fullNameId;
    }

    public void setFullNameId(String fullNameId) {
        this.fullNameId = fullNameId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNoId() {
        return phoneNoId;
    }

    public void setPhoneNoId(String phoneNoId) {
        this.phoneNoId = phoneNoId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getEmailId() {
        return emailId;


    }
*/
}
