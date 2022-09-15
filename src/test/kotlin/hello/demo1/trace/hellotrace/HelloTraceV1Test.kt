package hello.demo1.trace.hellotrace

import hello.demo1.trace.TraceStatus
import org.junit.jupiter.api.Test

internal class HelloTraceV1Test{

    @Test
    fun begin_end(){
        val trace : HelloTraceV2 = HelloTraceV2()
        val status : TraceStatus = trace.begin("HELLO")
        trace.end(status)
    }

    @Test
    fun begin_ex(){
        val trace : HelloTraceV2 = HelloTraceV2()
        val status : TraceStatus = trace.begin("HELLO")
        trace.exception(status, IllegalAccessException("asdasd"))
    }
}