package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV2(
    private val orderRepositoryV2: OrderRepositoryV2,
    private val helloTraceV2: HelloTraceV2
) {
    fun orderItem(itemId: String, status : TraceStatus) {
        var newStatus : TraceStatus? = null
        try{
            newStatus = helloTraceV2.beginSync(status, "OrderService.orderItem()")
            orderRepositoryV2.save(itemId, newStatus)
            helloTraceV2.end(status)
        }catch(e:java.lang.Exception){
            helloTraceV2.exception(newStatus!!, e)
            throw e
        }


    }
}