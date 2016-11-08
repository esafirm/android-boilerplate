package com.esafirm.androidboilerplate;

import android.database.Cursor;
import com.esafirm.androidboilerplate.data.local.DatabaseHelper;
import com.esafirm.androidboilerplate.data.local.Db;
import com.esafirm.androidboilerplate.data.local.DbOpenHelper;
import com.esafirm.androidboilerplate.data.model.Ribot;
import com.esafirm.androidboilerplate.test.common.TestDataFactory;
import com.esafirm.androidboilerplate.util.DefaultConfig;
import com.esafirm.androidboilerplate.util.RxSchedulersOverrideRule;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

  private final DatabaseHelper mDatabaseHelper =
      new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));

  @Rule public final RxSchedulersOverrideRule mOverrideSchedulersRule =
      new RxSchedulersOverrideRule();

  @Test public void setRibots() {
    Ribot ribot1 = TestDataFactory.makeRibot("r1");
    Ribot ribot2 = TestDataFactory.makeRibot("r2");
    List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

    TestSubscriber<Ribot> result = new TestSubscriber<>();
    mDatabaseHelper.setRibots(ribots).subscribe(result);
    result.assertNoErrors();
    result.assertReceivedOnNext(ribots);

    Cursor cursor = mDatabaseHelper.getBriteDb()
        .query("SELECT * FROM " + Db.RibotProfileTable.INSTANCE.getTABLE_NAME());
    assertEquals(2, cursor.getCount());

    for (Ribot ribot : ribots) {
      cursor.moveToNext();
      assertEquals(ribot.getProfile(), Db.RibotProfileTable.INSTANCE.parseCursor(cursor));
    }
  }

  @Test public void getRibots() {
    Ribot ribot1 = TestDataFactory.makeRibot("r1");
    Ribot ribot2 = TestDataFactory.makeRibot("r2");
    List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

    mDatabaseHelper.setRibots(ribots).subscribe();

    TestSubscriber<List<Ribot>> result = new TestSubscriber<>();
    mDatabaseHelper.getRibots().subscribe(result);
    result.assertNoErrors();
    result.assertValue(ribots);
  }
}
