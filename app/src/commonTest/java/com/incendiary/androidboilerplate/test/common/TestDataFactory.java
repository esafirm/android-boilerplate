package com.incendiary.androidboilerplate.test.common;

import com.incendiary.androidboilerplate.data.model.Name;
import com.incendiary.androidboilerplate.data.model.Profile;
import com.incendiary.androidboilerplate.data.model.Ribot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

  public static String randomUuid() {
    return UUID.randomUUID().toString();
  }

  public static Ribot makeRibot(String uniqueSuffix) {
    return new Ribot(makeProfile(uniqueSuffix));
  }

  public static List<Ribot> makeListRibots(int number) {
    List<Ribot> ribots = new ArrayList<>();
    for (int i = 0; i < number; i++) {
      ribots.add(makeRibot(String.valueOf(i)));
    }
    return ribots;
  }

  public static Profile makeProfile(String uniqueSuffix) {
    return Profile.builder()
        .setName(makeName(uniqueSuffix))
        .setEmail("email" + uniqueSuffix + "@ribot.co.uk")
        .setDateOfBirth(new Date())
        .setHexColor("#0066FF")
        .setAvatar("http://api.ribot.io/images/" + uniqueSuffix)
        .setBio(randomUuid())
        .build();
  }

  public static Name makeName(String uniqueSuffix) {
    return new Name("Name-" + uniqueSuffix, "Surname-" + uniqueSuffix);
  }
}
