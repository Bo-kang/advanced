package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV2(
    private val orderServiceV2: OrderServiceV2,
    private val helloTraceV2: HelloTraceV2
) {
    @GetMapping("/v2/request")
    fun request(itemId : String): String {
        var status : TraceStatus? = null
        try{
            status = helloTraceV2.begin("OrderController.request()")
            orderServiceV2.orderItem(itemId, status)
            helloTraceV2.end(status)
        }catch(e:java.lang.Exception){
            helloTraceV2.exception(status!!, e)
            throw e
        }

        return "OK"
    }
}