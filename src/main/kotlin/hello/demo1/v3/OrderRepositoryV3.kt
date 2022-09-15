package hello.demo1.v1

import hello.demo1.trace.TraceStatus
import hello.demo1.trace.hellotrace.HelloTraceV2
import hello.demo1.trace.logtrace.LogTrace
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class OrderRepositoryV3(
    private val trace: LogTrace
) {
    fun save(itemId : String ){

        var newStatus : TraceStatus? = null
        try{
            newStatus = trace.begin("OrderRepository.save()")
            if(itemId == "ex"){
                throw  IllegalAccessException("예외")
            }
            sleep(1000);
            trace.end(newStatus)
        }catch(e:java.lang.Exception){
            trace.exception(newStatus!!, e)
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