package com.esafirm.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Ribot implements Comparable<Ribot>, Parcelable {

  private Profile profile;

  public Profile getProfile() {
    return profile;
  }

  public Ribot(Profile profile) {
    this.profile = profile;
  }

  @Override public int compareTo(@NonNull Ribot another) {
    return profile.getName()
        .getFirst()
        .compareToIgnoreCase(another.getProfile().getName().getFirst());
  }

  /* --------------------------------------------------- */
  /* > Parcelable */
  /* --------------------------------------------------- */

  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.profile, flags);
  }

  protected Ribot(Parcel in) {this.profile = in.readParcelable(Profile.class.getClassLoader());}

  public static final Creator<Ribot> CREATOR = new Creator<Ribot>() {
    @Override public Ribot createFromParcel(Parcel source) {return new Ribot(source);}

    @Override public Ribot[] newArray(int size) {return new Ribot[size];}
  };
}

