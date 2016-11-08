package com.esafirm.androidboilerplate.data.local

import android.content.ContentValues
import android.database.Cursor
import com.esafirm.androidboilerplate.data.model.Name
import com.esafirm.androidboilerplate.data.model.Profile
import java.util.*

class Db {

	object RibotProfileTable {
		val TABLE_NAME = "ribot_profile"

		val COLUMN_EMAIL = "email"
		val COLUMN_FIRST_NAME = "first_name"
		val COLUMN_LAST_NAME = "last_name"
		val COLUMN_HEX_COLOR = "hex_color"
		val COLUMN_DATE_OF_BIRTH = "date_of_birth"
		val COLUMN_AVATAR = "avatar"
		val COLUMN_BIO = "bio"

		val CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
				COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
				COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
				COLUMN_LAST_NAME + " TEXT NOT NULL, " +
				COLUMN_HEX_COLOR + " TEXT NOT NULL, " +
				COLUMN_DATE_OF_BIRTH + " INTEGER NOT NULL, " +
				COLUMN_AVATAR + " TEXT, " +
				COLUMN_BIO + " TEXT" +
				" ); "

		fun toContentValues(profile: Profile): ContentValues {
			val values = ContentValues()
			values.put(COLUMN_EMAIL, profile.email)
			values.put(COLUMN_FIRST_NAME, profile.name.first)
			values.put(COLUMN_LAST_NAME, profile.name.last)
			values.put(COLUMN_HEX_COLOR, profile.hexColor)
			values.put(COLUMN_DATE_OF_BIRTH, profile.dateOfBirth.time)
			values.put(COLUMN_AVATAR, profile.avatar)
			if (profile.bio != null) values.put(COLUMN_BIO, profile.bio)
			return values
		}

		fun parseCursor(cursor: Cursor): Profile {
			val name = Name(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME)),
					cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME)))
			val dobTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_BIRTH))

			return Profile.builder()
					.setName(name)
					.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)))
					.setHexColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEX_COLOR)))
					.setDateOfBirth(Date(dobTime))
					.setAvatar(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR)))
					.setBio(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO)))
					.build()
		}
	}
}
