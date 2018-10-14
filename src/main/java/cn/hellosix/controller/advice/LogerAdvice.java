package cn.hellosix.controller.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lzz
 */
@Aspect
@Component
public class LogerAdvice {
    private static final Log logger = LogFactory.getLog("access");
    private static final String LOG_SEPARATOR = ",";
    /**
     * 用于记录 access log
     * @param proceedingJoinPoint
     * @return
     */
    @Around( "execution(* cn.hellosix.controller.*.*(..))" )
    public  Object arroundExecuteController( ProceedingJoinPoint proceedingJoinPoint ){
        Object value = accessInit(proceedingJoinPoint);
        return value;
    }

    private Object accessInit(ProceedingJoinPoint proceedingJoinPoint) {
        Object value = null;
        try {
            /* 客户端ip */
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String clientIp = request.getHeader("x-forwarded-for");
            if ( clientIp == null ){
                clientIp = request.getRemoteHost();
            }
            String path = request.getServletPath();
            /* 请求参数 */
            Object[] params = proceedingJoinPoint.getArgs();
            String param = "";
            if( null != params && params.length != 0 ){
                param = params[0].toString();
            }
            /* 计算调用时间 */
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            String msgType;
            if( stopWatch.getTotalTimeMillis() > 100 ){
                String msg =  LOG_SEPARATOR + clientIp + LOG_SEPARATOR + path + LOG_SEPARATOR + param + LOG_SEPARATOR;
                System.out.println( msg );
            }

            stopWatch.stop();
        } catch (Throwable e) {
            logger.error( "", e );
        }
        return value;
    }
}
