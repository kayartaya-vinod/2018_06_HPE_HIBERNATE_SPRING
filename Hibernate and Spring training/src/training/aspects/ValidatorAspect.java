package training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import training.entity.Product;

//@Component
@Aspect
public class ValidatorAspect {
	
	@Around(value="execution(training.entity.Product training..*Dao.*(..))")
	public Object changeProductNameToUpperCase(ProceedingJoinPoint pjp) throws Throwable {
		
		Object obj =  pjp.proceed();
		Product p = (Product) obj;
		p.setName(p.getName().toUpperCase());
		
		return obj;
	}

	// ProceedingJoinPoint is only supported for around advice
	@Around("execution(* training..*Dao.*(Double, Double))")
	public Object validateAndSwap(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double x = (Double) args[0];
		Double y = (Double) args[1];

		if (x > y) {
			args = new Object[] { y, x };
		}

		return pjp.proceed(args);
	}
}
