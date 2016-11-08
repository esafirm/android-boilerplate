package com.esafirm.androidboilerplate.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import com.esafirm.androidboilerplate.R

object DialogFactory {

	fun createSimpleOkErrorDialog(context: Context, title: String, message: String): Dialog {
		return AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setNeutralButton(R.string.dialog_action_ok, null)
				.create()
	}

	fun createSimpleOkErrorDialog(context: Context, @StringRes titleResource: Int, @StringRes messageResource: Int): Dialog {
		return createSimpleOkErrorDialog(context, context.getString(titleResource),
				context.getString(messageResource))
	}

	fun createGenericErrorDialog(context: Context, message: String): Dialog {
		return AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.dialog_error_title))
				.setMessage(message)
				.setNeutralButton(R.string.dialog_action_ok, null)
				.create()
	}

	fun createGenericErrorDialog(context: Context, @StringRes messageResource: Int): Dialog {
		return createGenericErrorDialog(context, context.getString(messageResource))
	}

	fun createProgressDialog(context: Context, message: String): ProgressDialog {
		val progressDialog = ProgressDialog(context)
		progressDialog.setMessage(message)
		return progressDialog
	}

	fun createProgressDialog(context: Context,
	                         @StringRes messageResource: Int): ProgressDialog {
		return createProgressDialog(context, context.getString(messageResource))
	}
}
