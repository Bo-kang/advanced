package hello.demo1.config

import hello.demo1.trace.logtrace.FieldLogTrace
import hello.demo1.trace.logtrace.LogTrace
import hello.demo1.trace.logtrace.ThreadLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace() : LogTrace = ThreadLogTrace()
}