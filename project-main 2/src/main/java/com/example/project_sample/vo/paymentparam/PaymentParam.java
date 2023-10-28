package com.example.project_sample.vo.paymentparam;

import java.util.ArrayList;
import java.util.List;

import com.example.project_sample.vo.accommodation.AccVo;
import com.example.project_sample.vo.accommodation.RoomVo;
import com.example.project_sample.vo.member.MemberVo;

import lombok.Data;

@Data
public class PaymentParam {

    // String[] item_name;
    // int[] pay_count;
    // int[] pay_price;
    // int[] p_idx;

    int mem_idx;
    String mem_name;
    String room_name;
    int room_price;
    int acc_idx;
    int room_idx;
    String room_check_in;




}
