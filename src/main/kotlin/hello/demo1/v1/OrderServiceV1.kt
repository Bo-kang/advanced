package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV1
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderServiceV1(
    private val orderRepositoryV1: OrderRepositoryV1,
    private val helloTraceV1: HelloTraceV1
) {
    fun orderItem(itemId: String) {
        var status : TraceStatus? = null
        try{
            status = helloTraceV1.begin("OrderService.orderItem()")
            orderRepositoryV1.save(itemId)
            helloTraceV1.end(status)
        }catch(e:java.lang.Exception){
            helloTraceV1.exception(status!!, e)
            throw e
        }


    }
}