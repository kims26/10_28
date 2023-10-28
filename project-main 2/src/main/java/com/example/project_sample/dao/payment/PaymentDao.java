package com.example.project_sample.dao.payment;

import org.apache.ibatis.annotations.Mapper;

import com.example.project_sample.vo.payment.PaymentVo;

@Mapper
public interface PaymentDao {

    int insert(PaymentVo paymentvo);

    int selectPayNum();

}

