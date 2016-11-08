package com.incendiary.androidboilerplate.data.local

import android.database.sqlite.SQLiteDatabase
import com.incendiary.androidboilerplate.data.model.Ribot
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class DatabaseHelper
@Inject constructor(dbOpenHelper: DbOpenHelper) {

	val briteDb: BriteDatabase
			by lazy { SqlBrite.create().wrapDatabaseHelper(dbOpenHelper) }

	fun setRibots(newRibots: Collection<Ribot>): Observable<Ribot> {
		return Observable.create(Observable.OnSubscribe<Ribot> { subscriber ->
			if (subscriber.isUnsubscribed) return@OnSubscribe
			val transaction = briteDb.newTransaction()

			try {
				briteDb.delete(Db.RibotProfileTable.TABLE_NAME, null)
				for (ribot in newRibots) {
					val result = briteDb.insert(Db.RibotProfileTable.TABLE_NAME,
							Db.RibotProfileTable.toContentValues(ribot.profile),
							SQLiteDatabase.CONFLICT_REPLACE)
					if (result >= 0) subscriber.onNext(ribot)
				}

				transaction.markSuccessful()
				subscriber.onCompleted()
			} finally {
				transaction.end()
			}
		})
	}

	val ribots: Observable<List<Ribot>>
		get() = briteDb.createQuery(Db.RibotProfileTable.TABLE_NAME,
				"SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME).mapToList { cursor -> Ribot(Db.RibotProfileTable.parseCursor(cursor)) }
}
