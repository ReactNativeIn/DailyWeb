package com.daily.www.payment.dao;

import com.daily.www.payment.vo.PaymentVO;

public interface PaymentDAO {

	// 결제결과 등록
	int insertPayment(PaymentVO paymentVO);

}