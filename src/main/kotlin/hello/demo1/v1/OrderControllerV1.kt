package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV1
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV1(
    private val orderServiceV1: OrderServiceV1,
    private val helloTraceV1: HelloTraceV1
) {
    @GetMapping("/v1/request")
    fun request(itemId : String): String {
        var status : TraceStatus? = null
        try{
            status = helloTraceV1.begin("OrderController.request()")
            orderServiceV1.orderItem(itemId)
            helloTraceV1.end(status)
        }catch(e:java.lang.Exception){
            helloTraceV1.exception(status!!, e)
            throw e
        }

        return "OK"
    }
}