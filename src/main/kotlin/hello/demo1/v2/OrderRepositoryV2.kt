package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class OrderRepositoryV2(
    private val helloTraceV2: HelloTraceV2
) {
    fun save(itemId : String , status : TraceStatus){

        var newStatus : TraceStatus? = null
        try{
            newStatus = helloTraceV2.beginSync(status, "OrderRepository.save()")
            if(itemId == "ex"){
                throw  IllegalAccessException("예외")
            }
            sleep(1000);
            helloTraceV2.end(newStatus)
        }catch(e:java.lang.Exception){
            helloTraceV2.exception(newStatus!!, e)
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