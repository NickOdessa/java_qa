package com.qa.java.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id=Integer.MAX_VALUE;
  @Expose
  @Column (name = "firstname")
  private String firstname;
  @Expose
  @Column (name = "lastname")
  private String lastname;
  @Transient
  private String nickname;
  @Transient
  private String group;
  @Transient
  private String company;
  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private String address;
  @Transient
  private String homephone;
  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilephone;
  @Transient
  private String workphone;
  @Transient
  private String allphones;
  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;
  @Transient
  private String email2;
  @Transient
  private String email3;
  @Transient
  private String allEmails;
  @Expose
  @Transient
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }


  public int getId() {return id;}

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homephone;
  }

  public String getMobilePhone() {
    return mobilephone;
  }

  public String getWorkPhone() { return workphone; }

  public String getAllphones() {
    return allphones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() { return email2; }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getGroup(){
    return group;
  }

  public File getPhoto() {
    return new File(photo);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", photo='" + photo + '\'' +
            ", groups=" + groups +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
