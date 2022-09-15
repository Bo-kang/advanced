package hello.demo1.trace.logtrace

import hello.demo1.trace.TraceId
import hello.demo1.trace.TraceStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.StringBuilder

class ThreadLogTrace(

) : LogTrace {
    private val  traceIdHolder =  ThreadLocal<TraceId?>()
    val START_PREFIX = "-->"
    val COMPLETE_PREFIX = "<--"
    val EX_PREFIX = "<x-"

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTime = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId!!.id, addSpace(START_PREFIX, traceId.level), message)

        return TraceStatus(traceId, startTime, message)
    }

    private fun syncTraceId() {
        traceIdHolder.set(traceIdHolder.get()?.createNextId() ?: TraceId())
    }

    private fun releaseTraceId() {
        if (traceIdHolder.get() == null) {
            log.info("traceIdHolder is NULL")
        } else {
            traceIdHolder.set( if (traceIdHolder.get()?.isFirstLevel() == true) {
                traceIdHolder.remove()
                null
            }
            else
                traceIdHolder.get()?.createPrevId()
            )
        }
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception?) {
        complete(status, e)
    }


    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val res = stopTimeMs - status.startTimeMs
        if (e == null)
            log.info(
                "[{}] {}{} time={}ms", status.traceId.id,
                addSpace(COMPLETE_PREFIX, status.traceId.level), status.message, res
            )
        else
            log.info(
                "[{}] {}{} time={}ms ex={}", status.traceId.id,
                addSpace(EX_PREFIX, status.traceId.level), status.message, res, e.message
            )

        releaseTraceId()
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(javaClass)

        private fun addSpace(prefix: String, level: Int): String {
            val sb: java.lang.StringBuilder = StringBuilder()
            for (i: Int in 0 until level) {
                sb.append(
                    if (i == level - 1)
                        "|$prefix"
                    else
                        "|   "
                )
            }
            return sb.toString()
        }
    }
}