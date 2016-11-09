package com.esafirm.androidboilerplate.test.common

import com.esafirm.androidboilerplate.data.model.Name
import com.esafirm.androidboilerplate.data.model.Profile
import com.esafirm.androidboilerplate.data.model.Ribot
import com.esafirm.androidboilerplate.util.loop
import java.util.*

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
object TestDataFactory {

	fun randomUuid(): String {
		return UUID.randomUUID().toString()
	}

	fun makeRibot(uniqueSuffix: String): Ribot {
		return Ribot(makeProfile(uniqueSuffix))
	}

	fun makeListRibots(number: Int): List<Ribot> {
		val ribots = ArrayList<Ribot>()
		for (i in 0..number - 1) {
		}

		number.loop {
			ribots.add(makeRibot(it.toString()))
		}

		return ribots
	}

	fun makeProfile(uniqueSuffix: String): Profile {
		return Profile.builder()
				.setName(makeName(uniqueSuffix))
				.setEmail("email$uniqueSuffix@ribot.co.uk")
				.setDateOfBirth(Date())
				.setHexColor("#0066FF")
				.setAvatar("http://api.ribot.io/images/" + uniqueSuffix)
				.setBio(randomUuid()).build()
	}

	fun makeName(uniqueSuffix: String): Name {
		return Name("Name-" + uniqueSuffix, "Surname-" + uniqueSuffix)
	}
}
