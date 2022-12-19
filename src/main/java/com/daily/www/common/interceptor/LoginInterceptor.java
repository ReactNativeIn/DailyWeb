package com.daily.www.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

//==========================================================================
// redirect : 특정한 조건에 만족하지 않을경우 특정 페이지로 이동시킬때 사용한다.
// ex) 회원관련 페이지에서 로그인이 되어 있지 않다면 메인페이지 또는 로그인페이지로 이동시키기
// *Controller에서 매 요청이 올 때마다 로그인을 체크하고 redirect를 하는것은 효율적이지 못함.
// 이처럼 redirect를 사용해야 하는 경우가 많을때 인터셉터, 명확하게는 HandlerInterceptor를 이용한다.
//
// HandlerInterceptor는 interface로 3가지 method()를 포함하고 있다.
// preHandle()		: Controller가 작동 되기 전
// postHandle()		: Controller가 작업을 마친 후
// afterCompletion()	: Controller, View가 모두 작업을 마친 후
//
// * 기존에 클라이언트의 요청이 작동되던 순서
// ==> Request -> DispatcherServlet -> Handler(Controller) -> View -> Response
// * 인터셉터를 적용했을때 요청이 작동되는 순서
//		(해당 interface에 있는 3가지 메서드를 모두 적용시킨다는 가정하에서)
// ==> Request -> DispatcherServlet
//				-> preHandle -> Handler(Controller) -> postHandle -> View -> afterCompletion() -> response
//==========================================================================



//==========================================================================
// public class LoginInterceptor extends HandlerInterceptorAdapter
//==========================================================================
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	//==========================================================================
	// preHandle()
	// 전처리기는 클라이언트에서 컨트롤러로 요청할 때 가로채는 것이다.
	// 즉, 컨트롤러가 호출되기 전에 실행되는 메서드
	//==========================================================================
	// 컨트롤러가 작동되기 전에 세션이 있는지 검사하는 메서드
	//==========================================================================
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션 객체를 가져온다.
		HttpSession session = request.getSession();
		
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져온다.
		Object obj = session.getAttribute("member");
		
		// 정상적으로 로그인 하지 않아서 세션을 발급받지 못한 경우
		if(obj == null) {
			logger.info("LoginInterceptor preHandle() : 세션이 없습니다...");

			// 인코딩방식 설정하고 스크립트를 직접 입력해준다.
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 페이지입니다.'); location.replace('/member/loginForm');</script>");
            out.flush();

			out.close();
			// 로그인이 안되어 있는 상태이므로 로그인 페이지로 돌려보낸다.
			
			return false;	// 더이상 컨트롤러의 요청으로 가지 않도록 false로 반환한다.
		}
		
		// preHandle의 return은 컨트롤러의 요청 uri로 가도 되나 안되나 허가해주는 의미를 가짐
		// 따라서 true로 하면 컨트롤러의 uri로 가게된다.
		return true;
	}
	
	
	//==========================================================================
	// postHandle() : 컨트롤러가 수행이 되고 화면이 보여지기 직전에 수행되는 메서드
	// 후처리기는 컨트롤러에서 클라이언트로 요청할 때 가로채는 것이다.
	// 즉, 컨트롤러가 호출되고 난 후에 실행되는 메서드
	//==========================================================================
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		
		
		
	}

	//==========================================================================
	// afterCompletion() : 컨트롤러와 화면이 모두 실행된 후에 실행되는 메서드
	// 컨트롤러의 처리가 끝나고 화면처리까지 모두 끝나면 실행되는 메서드
	//==========================================================================
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
		
	}
	
	
	
	
	
	
}	// End - public class LoginInterceptor extends HandlerInterceptorAdapter
