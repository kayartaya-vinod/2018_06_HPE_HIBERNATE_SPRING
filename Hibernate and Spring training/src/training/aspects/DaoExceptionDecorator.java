package training.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import training.dao.DaoException;

@Component
@Aspect
public class DaoExceptionDecorator {

	@AfterThrowing(pointcut = "execution(* training..*Dao.*(..))", throwing = "ex")
	public void decorator(Exception ex) throws DaoException {
		throw new DaoException("This is a decorated exception", ex);
	}
}
