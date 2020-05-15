package com.jenish.core.data

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
data class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0
)