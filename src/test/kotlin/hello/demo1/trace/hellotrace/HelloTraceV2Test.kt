package hello.demo1.trace.hellotrace

import hello.demo1.trace.TraceStatus
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HelloTraceV2Test {

    @Test
    fun begin_end(){
        val trace : HelloTraceV2 = HelloTraceV2()
        val status : TraceStatus = trace.begin("HELLO")
        val status2 : TraceStatus = trace.beginSync(status, "HELLO")
        trace.end(status2)
        trace.end(status)
    }

    @Test
    fun begin_ex(){
        val trace : HelloTraceV2 = HelloTraceV2()
        val status : TraceStatus = trace.begin("HELLO")
        trace.exception(status, IllegalAccessException("asdasd"))
    }
}