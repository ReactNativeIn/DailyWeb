package com.daily.www.payment.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.payment.vo.PaymentVO;

@Repository
public class PaymentDAOImpl implements PaymentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "PaymentDAO";
	
	// 결제결과 등록
	@Override
	public int insertPayment(PaymentVO paymentVO) {
		return sqlSession.insert(NAMESPACE + ".insertPayment", paymentVO);
	}

}
