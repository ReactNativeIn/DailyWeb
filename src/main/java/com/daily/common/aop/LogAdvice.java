package com.daily.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.java.Log;

//===================================================
// public class LogAdvice
// @Aspect	: 해당 클래스 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용
// Aspect	: 흩어진 관심사를 모듈화 한 것이다. 주로 부가기능을 모듈화 한다.

// @Component : 컨트롤러나 서비스 같은 역할은 아니지만 Bean으로 등록하기 위해 사용
// AOP와 관계는 없지만 스프링에서 Bean으로 인식하기 위해 사용한다.
//===================================================
@Aspect
@Component
@Log
public class LogAdvice {
	
	// 롬복에 의해 private static Logger logger = LoggerFactory.getLogger(LogAdvice.class); 생략됨
	
	//==================================================================
	// Advice		: 실직적인 부가기능을 담은 구현체
	// @Before		: 타켓 메서드가 실행하기 이전 어드바이스 기능을 수행
	// @After		: 타겟 메서드의 결과에 상관없이 실행한 후 어드바이스 기능을 수행
	// @AfterReturning	: 타겟 메서드가 정상적으로 결과를 반환한 후에 어드바이스 기능을 수행
	// @AfterThrowing	: 타겟 메서드가 수행 중 예외를 발생시키면 어드바이스 기능을 수행
	// @Around		: 어드바이스가 타겟 메서드를 감싸서 타겟 메서드의 호출 전, 후 어드바이스 기능을 수행
	//==================================================================
	// execution(int minus(int, int))	: int 타입의 리턴값, 두 개의 int 파라미터를 가지는 minus메서드
	// execution(* minus(int, int))		: 모든 타입의 리턴값, 두 개의 int 파라미터를 가지는 minus메서드
	// execution(* minus(..))			: 리턴 타입, 파라미터의 종류, 개수에 상관없이 minus라는 메서드 이름을 가지는 모든 메서드
	// execution(* minus())				: 리턴 타입은 상관없이 파라미터가 존재하지 않는 minus 메서드
	// execution(* com.edu.aop.Target.*(..))	: com.edu.aop 패키지의 Target 클래스에 존재하는 모든 메서드
	// execution(* com.edu.aop.*.*(..))	: com.edu.aop 패키지에 존재하는 모든 클래스의 모든 메서드
	//==================================================================
	// @Around("execution(* com.edu.aop.service.AopService*.*(..))")
	// "execution()"	: AspectJ의 표현식
	// execution	: 접근제한자와 특정클래스의 메서드를 지정할 수 있음
	// 맨 앞의 *	: 리턴타입을 의미
	// 맨 뒤의 *	: 클래스의 이름과 메서드의 이름을 의미
	//==================================================================
	
	//==================================================================
	//  모든 패키지 내의 controller, service, dao 패키지에 존재하는 클래스
	//==================================================================
	@Around("execution(* com.daily.www..*.*(..))")
	public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("AOP Before가 실행됩니다.");
		
		// 해당 클래스의 처리 전 시간
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 해당 클래스의 메서드를 실행한다 ==> 핵심업무
		Object result = pjp.proceed();
		
		// 해당 클래스의 메서드가 처리된 후의 시간
		sw.stop();
		
		long executionTime = sw.getTotalTimeMillis();	// 총 걸린 시간
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		
		String task = className + "." + methodName;
		
		log.info("[업무처리소요시간] " + task + " ==> " + executionTime + "(ms)");
		
		return result;
		
	}
	
}	// End - public class LogAdvice

















