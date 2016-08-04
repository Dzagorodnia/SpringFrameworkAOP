package dmytro.learn.spring.framework.aop.loggins;

import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logger {

	@Pointcut("execution(public * *(..))")
	private void allPublicMethods() {
	}

	@Pointcut("execution(* get*(..))")
	private void anyGetMethod() {
	}

	@Pointcut("execution(* *List(..))")
	private void anyListMethod() {
	}

	@Pointcut("execution(* dmytro.learn.spring.framework.aop.interfaces.Manager.*(..))")
	private void implementManager() {
	}

	@Pointcut("within(dmytro.learn.spring.framework.aop.objects..*)")
	private void inObjectsPackageAndSub() {
	}

	@Pointcut("@annotation(dmytro.learn.spring.framework.aop.annotations.ShowTime)")
	private void showTimeAnnotation() {
	}

	@Pointcut("@annotation(dmytro.learn.spring.framework.aop.annotations.ShowResult)")
	private void showResultAnnotation() {
	}

	@Pointcut("execution(* *(String))")
	private void paramString() {
	}

	@Around("inObjectsPackageAndSub()")
	public Object watchTime(ProceedingJoinPoint joinPoint) {
		Object output = null;
		long start = System.currentTimeMillis();

		System.out.println("method " + joinPoint.getSignature().toShortString() + " begin");

		for (Object object : joinPoint.getArgs()) {
			System.out.println("parameter: " + object);
		}

		try {
			// output = joinPoint.proceed(new Object[] {"C:\\Windows"});
			output = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;

		System.out.println("method " + joinPoint.getSignature().toShortString() + " end, time: " + time + "ms" + "\n");

		return output;

	}

	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "execution(java.util.Map *(..)) && showResultAnnotation()", returning = "object")
	public void printMap(Object object) {
		System.out.println("Map:");
		Map map = (Map) object;
		for (Object objectMap : map.keySet()) {
			System.out.println("key: " + objectMap + ", " + map.get(objectMap));
		}
		System.out.println();
	}

	@AfterReturning(pointcut = "execution(java.util.Set *(..)) && showResultAnnotation()", returning = "object")
	public void printSet(Object object) {
		System.out.println("Set:");
		Set set = (Set) object;
		for (Object objectSet : set) {
			System.out.println(objectSet);
		}
		System.out.println();
	}

	public void print(Object object) {
		System.out.println(object);
	}

	public void init() {
		System.out.println("init");
	}

	public void close() {
		System.out.println("close");
	}

}
