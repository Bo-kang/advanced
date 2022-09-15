package hello.demo1.v0

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class OrderRepositoryV0 {
    fun save(itemId : String){
        if(itemId == "ex"){
            throw  IllegalAccessException("예외")
        }
        sleep(1000);
    }
    fun sleep(millis : Long){
        try{
            Thread.sleep(millis)
        } catch (e : InterruptedException ){
            e.printStackTrace()
        }
    }
}