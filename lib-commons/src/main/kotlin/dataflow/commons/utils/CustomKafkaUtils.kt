package dataflow.commons.utils

import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message

fun acknowledgement(message: Message<*>): Acknowledgment? {
    return message.headers.get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment::class.java)
}
