package com.incendiary.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

public class Profile implements Parcelable {

  private Name name;
  private String email;
  private String hexColor;
  private Date dateOfBirth;
  private String bio;
  private String avatar;

  public Name getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getHexColor() {
    return hexColor;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getBio() {
    return bio;
  }

  public String getAvatar() {
    return avatar;
  }

  public Profile(Name name, String email, String hexColor, Date dateOfBirth, String bio,
      String avatar) {
    this.name = name;
    this.email = email;
    this.hexColor = hexColor;
    this.dateOfBirth = dateOfBirth;
    this.bio = bio;
    this.avatar = avatar;
  }

  public static ProfileBuilder builder(){
    return new ProfileBuilder();
  }

  /* --------------------------------------------------- */
  /* > Parcelable */
  /* --------------------------------------------------- */

  public Profile() {}

  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.name, flags);
    dest.writeString(this.email);
    dest.writeString(this.hexColor);
    dest.writeLong(this.dateOfBirth != null
        ? this.dateOfBirth.getTime()
        : -1);
    dest.writeString(this.bio);
    dest.writeString(this.avatar);
  }

  protected Profile(Parcel in) {
    this.name = in.readParcelable(Name.class.getClassLoader());
    this.email = in.readString();
    this.hexColor = in.readString();
    long tmpDateOfBirth = in.readLong();
    this.dateOfBirth = tmpDateOfBirth == -1
        ? null
        : new Date(tmpDateOfBirth);
    this.bio = in.readString();
    this.avatar = in.readString();
  }

  public static final Creator<Profile> CREATOR = new Creator<Profile>() {
    @Override public Profile createFromParcel(Parcel source) {return new Profile(source);}

    @Override public Profile[] newArray(int size) {return new Profile[size];}
  };
}
