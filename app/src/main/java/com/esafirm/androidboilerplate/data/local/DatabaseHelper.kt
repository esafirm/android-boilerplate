package com.esafirm.androidboilerplate.data.local

import android.database.sqlite.SQLiteDatabase
import com.esafirm.androidboilerplate.data.model.Ribot
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import rx.Emitter
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class DatabaseHelper
@Inject constructor(dbOpenHelper: DbOpenHelper) {

	val briteDb: BriteDatabase
			by lazy { SqlBrite.create().wrapDatabaseHelper(dbOpenHelper) }

	fun setRibots(newRibots: Collection<Ribot>): Observable<Ribot> =
			Observable.fromEmitter({
				val transaction = briteDb.newTransaction()

				try {
					briteDb.delete(Db.RibotProfileTable.TABLE_NAME, null)

					newRibots.forEach { item ->
						val result = briteDb.insert(Db.RibotProfileTable.TABLE_NAME,
								Db.RibotProfileTable.toContentValues(item.profile),
								SQLiteDatabase.CONFLICT_REPLACE)

						if (result >= 0) {
							it.onNext(item)
						}
					}

					transaction.markSuccessful()
					it.onCompleted()
				} finally {
					transaction.end()
				}
			}, Emitter.BackpressureMode.BUFFER)

	val ribots: Observable<List<Ribot>>
		get() = briteDb.createQuery(Db.RibotProfileTable.TABLE_NAME,
				"SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME).mapToList { cursor -> Ribot(Db.RibotProfileTable.parseCursor(cursor)) }
}
