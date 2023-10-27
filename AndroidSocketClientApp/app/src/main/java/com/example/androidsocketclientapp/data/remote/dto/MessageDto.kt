package com.example.androidsocketclientapp.data.remote.dto


import com.example.androidsocketclientapp.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.Date

@Serializable
data class MessageDto(
    val text: String,
    val timeStamp: Long,
    val userName: String,
    val id: String
) {
    fun toMessage(): Message {
        val date = Date(timeStamp)
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(date)
        return Message(
            text = text,
            formattedTime = formattedDate,
            username = userName
        )
    }
}