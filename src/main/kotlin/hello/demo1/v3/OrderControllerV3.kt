package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import hello.demo1.trace.logtrace.LogTrace
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class OrderControllerV3(
    private val orderServiceV3: OrderServiceV3,
    private val trace: LogTrace
) {
    @GetMapping("/v3/request")
    fun request(itemId : String): String {
        var status : TraceStatus? = null
        try{
            status = trace.begin("OrderController.request()")
            orderServiceV3.orderItem(itemId)
            trace.end(status)
        }catch(e:java.lang.Exception){
            trace.exception(status!!, e)
            throw e
        }

        return "OK"
    }
}