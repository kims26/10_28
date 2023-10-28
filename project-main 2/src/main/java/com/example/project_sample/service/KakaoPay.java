package com.example.project_sample.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.project_sample.vo.accommodation.AccVo;
import com.example.project_sample.vo.accommodation.KakaoPayApprovalVO;
import com.example.project_sample.vo.accommodation.KakaoPayReadyVO;
import com.example.project_sample.vo.accommodation.RoomVo;
import com.example.project_sample.vo.member.MemberVo;
import com.example.project_sample.vo.paymentparam.PaymentParam;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    PaymentParam globalPaymentParam;

    /**
     * @return
     */
    public String kakaoPayReady(PaymentParam payment) {

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "a47d0d64e01a12de0e35ecbb280d5890");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // kakaoPayReadyVO = null;

        System.out.println("-------------------------------------------------------------");
        System.out.println(payment);
        System.out.println(payment.getMem_name());
        String mem_name = payment.getMem_name();
        String room_name = payment.getRoom_name();
        int room_price = payment.getRoom_price();
        int mem_idx = payment.getMem_idx();
        int acc_idx = payment.getAcc_idx();
        int room_idx = payment.getRoom_idx();

        globalPaymentParam = payment;

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", mem_name);
        params.add("item_name", room_name);
        params.add("quantity", "1");
        params.add("total_amount", 2100+"");
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:9090/kakaoPaySuccess?" + "mem_idx=" + mem_idx + "&acc_idx=" + acc_idx+ "&room_idx=" + room_idx);
        params.add("cancel_url", "http://localhost:9090/kakaoPayCancel");
        params.add("fail_url", "http://localhost:9090/kakaoPaySuccessFail");

  


        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,
                    KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);
            System.out.println("-------------------------------------------------");
            System.out.println(kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {

            e.printStackTrace();
        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        return "/pay";

    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "a47d0d64e01a12de0e35ecbb280d5890");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        kakaoPayApprovalVO = null;


        String member_name = globalPaymentParam.getMem_name();
        // String room_name = globalPaymentParam.getRoom_name();
        // int room_price = payment.getRoom_price();
        // int mem_idx = payment.getMem_idx();
        // int acc_idx = payment.getAcc_idx();
        // int room_idx = payment.getRoom_idx();


        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", member_name);
        params.add("pg_token", pg_token);
        params.add("total_amount", 2100+"");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {

            e.printStackTrace();
        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        return null;
    }

}
