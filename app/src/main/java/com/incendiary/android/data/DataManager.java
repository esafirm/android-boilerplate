package com.incendiary.android.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import com.incendiary.android.data.local.DatabaseHelper;
import com.incendiary.android.data.model.Ribot;
import com.incendiary.android.data.remote.ApiService;

@Singleton public class DataManager {

  private final ApiService mApiService;
  private final DatabaseHelper mDatabaseHelper;

  @Inject public DataManager(ApiService apiService, DatabaseHelper databaseHelper) {
    mApiService = apiService;
    mDatabaseHelper = databaseHelper;
  }

  public Observable<Ribot> syncRibots() {
    return mApiService.getRibots().concatMap(mDatabaseHelper::setRibots);
  }

  public Observable<List<Ribot>> getRibots() {
    return mDatabaseHelper.getRibots().distinct();
  }
}