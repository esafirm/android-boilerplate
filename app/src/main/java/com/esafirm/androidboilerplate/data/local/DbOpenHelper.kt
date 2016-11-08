package com.esafirm.androidboilerplate.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.esafirm.androidboilerplate.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbOpenHelper
@Inject
constructor(@ApplicationContext context: Context) :
		SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	companion object {
		val DATABASE_NAME = "ribots.db"
		val DATABASE_VERSION = 2
	}

	override fun onConfigure(db: SQLiteDatabase) {
		super.onConfigure(db)
		//Uncomment line below if you want to enable foreign keys
		//db.execSQL("PRAGMA foreign_keys=ON;");
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.beginTransaction()
		try {
			db.execSQL(Db.RibotProfileTable.CREATE)
			//Add other tables here
			db.setTransactionSuccessful()
		} finally {
			db.endTransaction()
		}
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}
}
