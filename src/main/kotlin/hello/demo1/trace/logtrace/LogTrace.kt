package hello.demo1.trace.logtrace

import hello.demo1.trace.TraceStatus

interface LogTrace {

    fun begin(message : String) : TraceStatus
    fun end(status : TraceStatus)
    fun exception(status : TraceStatus , e : Exception?)
}