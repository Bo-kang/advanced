package hello.demo1.trace

import java.util.*

data class TraceId(var id: String? = null, var level: Int = 0) {


    init {
        this.id = id ?: createId()
        this.level = level ?: 0
    }

    private fun createId() : String{
        return UUID.randomUUID().toString().substring(0, 8)
    }

    fun createNextId(): TraceId = TraceId(id, level + 1)


    fun createPrevId() : TraceId =  TraceId(id, level-1)


    fun isFirstLevel() : Boolean = level == 0


}