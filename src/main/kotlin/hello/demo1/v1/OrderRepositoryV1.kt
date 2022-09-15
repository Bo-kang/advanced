package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV1
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class OrderRepositoryV1(
    private val helloTraceV1: HelloTraceV1
) {
    fun save(itemId : String){

        var status : TraceStatus? = null
        try{
            status = helloTraceV1.begin("OrderRepository.save()")
            if(itemId == "ex"){
                throw  IllegalAccessException("예외")
            }
            sleep(1000);
            helloTraceV1.end(status)
        }catch(e:java.lang.Exception){
            helloTraceV1.exception(status!!, e)
            throw e
        }


    }
    fun sleep(millis : Long){
        try{
            Thread.sleep(millis)
        } catch (e : InterruptedException ){
            e.printStackTrace()
        }
    }
}