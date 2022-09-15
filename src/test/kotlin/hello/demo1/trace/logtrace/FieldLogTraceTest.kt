package hello.demo1.trace.logtrace

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FieldLogTraceTest {

    private val trace : FieldLogTrace = FieldLogTrace()

    @Test
    fun begin() {

        val status : TraceStatus = trace.begin("HELLO")
        val status2 : TraceStatus = trace.begin( "HELLO")
        trace.end(status2)
        trace.end(status)
    }
}