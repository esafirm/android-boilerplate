package com.esafirm.androidboilerplate.data.model;

import java.util.Date;

public class ProfileBuilder {
  private Name name;
  private String email;
  private String hexColor;
  private Date dateOfBirth;
  private String bio;
  private String avatar;

  public ProfileBuilder setName(Name name) {
    this.name = name;
    return this;
  }

  public ProfileBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public ProfileBuilder setHexColor(String hexColor) {
    this.hexColor = hexColor;
    return this;
  }

  public ProfileBuilder setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  public ProfileBuilder setBio(String bio) {
    this.bio = bio;
    return this;
  }

  public ProfileBuilder setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public Profile build() {
    return new Profile(name, email, hexColor, dateOfBirth, bio, avatar);
  }
}
