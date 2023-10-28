package com.example.project_sample.vo.payment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentVo {
    int pay_idx;
    // int pay_num;
    // int pay_p_num;
    int pay_count;
    int pay_price;
    String pay_type;
    String pay_regdate;
    int mem_idx;
    int acc_idx;
    int room_idx;
}

