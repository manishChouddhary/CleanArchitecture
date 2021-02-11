package com.rxsense.cleanarchitecture.commoncomponents

import java.util.*

/**
 *
 */
fun String.formattedDate(): String {
    val currentFormat = "yyyy-MM-dd"
    val pattern = "MMMM d, yyyy"
    var simpleDateFormat = java.text.SimpleDateFormat(currentFormat, Locale.ENGLISH)
    val date = simpleDateFormat.parse(this)
    simpleDateFormat = java.text.SimpleDateFormat(pattern, Locale.ENGLISH)
    if (date == null) return ""
    return simpleDateFormat.format(date)
}
