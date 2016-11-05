package com.incendiary.androidboilerplate.data.local;

import android.database.sqlite.SQLiteDatabase;
import com.incendiary.androidboilerplate.data.model.Ribot;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

@Singleton public class DatabaseHelper {

  private final BriteDatabase mDb;

  @Inject public DatabaseHelper(DbOpenHelper dbOpenHelper) {
    mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper);
  }

  public BriteDatabase getBriteDb() {
    return mDb;
  }

  public Observable<Ribot> setRibots(final Collection<Ribot> newRibots) {
    return Observable.create(new Observable.OnSubscribe<Ribot>() {
      @Override public void call(Subscriber<? super Ribot> subscriber) {
        if (subscriber.isUnsubscribed()) return;
        BriteDatabase.Transaction transaction = mDb.newTransaction();

        try {
          mDb.delete(Db.RibotProfileTable.INSTANCE.getTABLE_NAME(), null);
          for (Ribot ribot : newRibots) {
            long result = mDb.insert(Db.RibotProfileTable.INSTANCE.getTABLE_NAME(),
                Db.RibotProfileTable.INSTANCE.toContentValues(ribot.profile()),
                SQLiteDatabase.CONFLICT_REPLACE);
            if (result >= 0) subscriber.onNext(ribot);
          }

          transaction.markSuccessful();
          subscriber.onCompleted();
        } finally {
          transaction.end();
        }
      }
    });
  }

  public Observable<List<Ribot>> getRibots() {
    return mDb.createQuery(Db.RibotProfileTable.INSTANCE.getTABLE_NAME(),
        "SELECT * FROM " + Db.RibotProfileTable.INSTANCE.getTABLE_NAME())
        .mapToList(cursor -> Ribot.create(Db.RibotProfileTable.INSTANCE.parseCursor(cursor)));
  }
}
