package training.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//@Component
@Aspect
public class LoggerAspect {
	
	@Before(value="execution(* training..*Dao.*(..))")
	public void logBeforeMethodCall(JoinPoint jp) {
		System.out.println("\n>>>>>Logging before the method " 
				+ jp.getSignature().getName() + " with arguments "
				+ Arrays.toString(jp.getArgs()));
		
	}
}
