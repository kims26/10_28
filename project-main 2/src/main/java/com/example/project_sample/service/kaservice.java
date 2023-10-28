// package demo_final.service;

// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.client.RestTemplate;

// import demo_final.vo.KakaoApproveResponse;
// import demo_final.vo.KakaoReadyResponse;
// import demo_final.vo.PaymentParam;
// import lombok.Getter;
// import lombok.RequiredArgsConstructor;
// import lombok.Setter;
// import lombok.ToString;

// @Service
// @RequiredArgsConstructor
// @Transactional
// @Getter
// @Setter
// @ToString
// public class KakaoPayService {

//     static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
//     static final String admin_Key = "65781a7475ca643441816c48c0a0a28f"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
//     private KakaoReadyResponse kakaoReady;

//     public KakaoReadyResponse kakaoPayReady(PaymentParam payment) {

//         System.out.println("--------------------------------------------------");
//         System.out.println(payment);
//         System.out.println("--------------------------------------------------");

//         String item_name = "";
//         int count = 0;
//         int price = 0;

//         if (payment.getItem_name().size() == 1) {
//             item_name = payment.getItem_name().get(0);
//             count = payment.getPay_count().get(0);
//             price = payment.getPay_price().get(0);
//         } else {

//             item_name = payment.getItem_name().get(0) + "외 " + (payment.getItem_name().size() - 1);

//             for (int i = 0; i < payment.getItem_name().size(); i++) {

//                 count += payment.getPay_count().get(i);
//                 price += (payment.getPay_price().get(i) * payment.getPay_count().get(i));

//                 // System.out.printf("price:%d payment.getPay_price().get(%d):%d\n", price, i,
//                 // payment.getPay_price().get(i));

//             }

//         }

//         // 카카오페이 요청 양식
//         MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//         parameters.add("cid", cid);
//         parameters.add("partner_order_id", "가맹점 주문 번호");
//         parameters.add("partner_user_id", "가맹점 회원 ID");
//         parameters.add("item_name", item_name);
//         parameters.add("quantity", count + "");
//         parameters.add("total_amount", price + "");
//         parameters.add("vat_amount", "0");
//         parameters.add("tax_free_amount", "0");
//         parameters.add("approval_url", "http://192.168.0.82:9090/payment/success"); // 성공 시 redirect url
//         parameters.add("cancel_url", "http://192.168.0.82:9090/payment/cancel"); // 취소 시 redirect url
//         parameters.add("fail_url", "http://192.168.0.82:9090/payment/fail"); // 실패 시 redirect url

//         // 파라미터, 헤더
//         HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

//         // 외부에 보낼 url
//         RestTemplate restTemplate = new RestTemplate();

//         kakaoReady = restTemplate.postForObject(
//                 "https://kapi.kakao.com/v1/payment/ready",
//                 requestEntity,
//                 KakaoReadyResponse.class);

//         return kakaoReady;
//     }

//     /**
//      * 결제 완료 승인
//      */
//     public KakaoApproveResponse ApproveResponse(String pgToken) {

//         // 카카오 요청
//         MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//         parameters.add("cid", cid);
//         parameters.add("tid", kakaoReady.getTid());
//         parameters.add("partner_order_id", "가맹점 주문 번호");
//         parameters.add("partner_user_id", "가맹점 회원 ID");
//         parameters.add("pg_token", pgToken);

//         // 파라미터, 헤더
//         HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

//         // 외부에 보낼 url
//         RestTemplate restTemplate = new RestTemplate();

//         KakaoApproveResponse approveResponse = restTemplate.postForObject(
//                 "https://kapi.kakao.com/v1/payment/approve",
//                 requestEntity,
//                 KakaoApproveResponse.class);

//         return approveResponse;
//     }

//     /**
//      * 카카오 요구 헤더값
//      */
//     private HttpHeaders getHeaders() {
//         HttpHeaders httpHeaders = new HttpHeaders();

//         String auth = "KakaoAK " + admin_Key;

//         httpHeaders.set("Authorization", auth);
//         httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

//         return httpHeaders;
//     }

// }
