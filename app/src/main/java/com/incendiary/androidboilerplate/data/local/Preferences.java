package com.incendiary.androidboilerplate.data.local;

import android.app.Application;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

public class Preferences {

  private static Preferences INSTANCE;

  public static Preferences getInstance() {
    if (INSTANCE == null) INSTANCE = new Preferences();
    return INSTANCE;
  }

  /* --------------------------------------------------- */
  /* > Setup */
  /* --------------------------------------------------- */

  public static void setup(Application application) {
    Hawk.init(application)
        .setEncryption(new NoEncryption())
        .build();
  }
}
