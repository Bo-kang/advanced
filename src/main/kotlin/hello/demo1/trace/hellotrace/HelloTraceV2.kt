package hello.demo1.trace.hellotrace

import hello.demo1.trace.TraceId
import hello.demo1.trace.TraceStatus
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.StringBuilder

@Slf4j
@Component
class HelloTraceV2 {

    val START_PREFIX = "-->"
    val COMPLETE_PREFIX = "<--"
    val EX_PREFIX = "<x-"


    fun begin(message : String) : TraceStatus {
        val traceId = TraceId()
        val startTime = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTime, message)
    }

    fun beginSync(status : TraceStatus, message : String) : TraceStatus{
        val traceId = status.traceId.createNextId()
        val startTime = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTime, message)
    }

    fun end(status : TraceStatus){
        complete(status, null)
    }

    fun exception(status : TraceStatus , e : Exception){
        complete(status, e)
    }

    private fun complete(status : TraceStatus , e : Exception?){
        val stopTimeMs = System.currentTimeMillis()
        val res = stopTimeMs - status.startTimeMs
        if(e == null)
            log.info("[{}] {}{} time={}ms", status.traceId.id, addSpace(COMPLETE_PREFIX, status.traceId.level), status.message, res)
        else
            log.info("[{}] {}{} time={}ms ex={}", status.traceId.id, addSpace(EX_PREFIX, status.traceId.level), status.message, res, e.message)
    }

    companion object{
        private val log : Logger = LoggerFactory.getLogger(javaClass)

        private fun addSpace(prefix : String, level : Int): String {
            val sb : java.lang.StringBuilder = StringBuilder()
            for( i : Int in 0 until level){
                sb.append(if(i == level -1)
                    "|$prefix"
                else
                    "|   " )
            }
            return sb.toString()
        }
    }
}