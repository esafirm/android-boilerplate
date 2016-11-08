package com.esafirm.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Name implements Parcelable {

  @SerializedName("first") private String first;
  @SerializedName("last") private String last;

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public Name(String first, String last) {
    this.first = first;
    this.last = last;
  }

  /* --------------------------------------------------- */
  /* > Parcelable */
  /* --------------------------------------------------- */

  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.first);
    dest.writeString(this.last);
  }

  public Name() {}

  protected Name(Parcel in) {
    this.first = in.readString();
    this.last = in.readString();
  }

  public static final Creator<Name> CREATOR = new Creator<Name>() {
    @Override public Name createFromParcel(Parcel source) {return new Name(source);}

    @Override public Name[] newArray(int size) {return new Name[size];}
  };
}
