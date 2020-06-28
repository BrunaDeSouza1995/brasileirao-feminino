package com.poc.bruna.brasileiraofeminino.plugin.extension

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"
private const val DATE_VIEW_FORMAT = "dd MMMM yyyy - HH:mm"

fun String?.formattedDate(): String {
    return try {
        val receivedDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val receivedDate = this?.let { receivedDateFormat.parse(this) }
        DateFormat.format(DATE_VIEW_FORMAT, receivedDate).toString()
    } catch (e: Exception) {
        ""
    }
}
