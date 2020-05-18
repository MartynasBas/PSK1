package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@NotFoundInvocation
public class NotFoundLogger {
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        System.out.println("Interceptor called");
        Object result;
        try {
            result = ctx.proceed();
        } catch (Exception e){
            return "errorpage";
        }

        return result;
    }
}
