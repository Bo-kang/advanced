package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import hello.demo1.trace.logtrace.LogTrace
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV3(
    private val orderRepositoryV3: OrderRepositoryV3,
    private val trace: LogTrace
) {
    fun orderItem(itemId: String) {
        var newStatus : TraceStatus? = null
        try{
            newStatus = trace.begin("OrderService.orderItem()")
            orderRepositoryV3.save(itemId)
            trace.end(newStatus)
        }catch(e:java.lang.Exception){
            trace.exception(newStatus!!, e)
            throw e
        }


    }
}