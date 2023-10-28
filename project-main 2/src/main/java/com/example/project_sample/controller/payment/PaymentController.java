package com.example.project_sample.controller.payment;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_sample.dao.accommodation.AccDao;
import com.example.project_sample.dao.accommodation.RoomDao;
import com.example.project_sample.dao.member.MemberDao;
import com.example.project_sample.dao.payment.PaymentDao;
import com.example.project_sample.service.KakaoPay;
import com.example.project_sample.vo.accommodation.AccVo;
import com.example.project_sample.vo.accommodation.KakaoPayApprovalVO;
import com.example.project_sample.vo.accommodation.RoomVo;
import com.example.project_sample.vo.member.MemberVo;
import com.example.project_sample.vo.payment.PaymentVo;
import com.example.project_sample.vo.paymentparam.PaymentParam;

import lombok.extern.java.Log;


@SpringBootApplication(scanBasePackages = {"com.example.project_sample.controller.payment", "com.example.project_sample.vo.accommodation"})
@Log
@Controller
public class PaymentController {
    
    @Autowired
    MemberDao memDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    AccDao accDao;

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    RoomVo roomvo;

    @Autowired
    AccVo accvo;

    PaymentParam globalPaymentParam;


    @Autowired
    private KakaoPay kakaopay;
    
    //결제 관련 예약 정보 넘기고 카카오페이ready로 넘어가기
    @RequestMapping("/payment.do")
    public String payment(RoomVo roomvo, AccVo accvo, MemberVo memvo, RedirectAttributes ra) {
        log.info("kakaoPay post............................................");
        
        System.out.println("-------------------------------------------------------------");
        System.out.println(accvo);
        System.out.println(roomvo);
        System.out.println(memvo);
        System.out.println("-------------------------------------------------------------");

        PaymentParam payment = new PaymentParam();

        payment.setMem_idx(memvo.getMem_idx());
        payment.setMem_name(memvo.getMem_name());
        payment.setRoom_name(roomvo.getRoom_name());
        payment.setRoom_price(roomvo.getRoom_price());
        payment.setAcc_idx(accvo.getAcc_idx());
        payment.setRoom_idx(roomvo.getRoom_idx());
        payment.setRoom_check_in(roomvo.getRoom_check_in());

        globalPaymentParam = payment;

        // ra.addAttribute("payment", payment);
   

        return "redirect:" + kakaopay.kakaoPayReady(payment);
 
    }




//     @PostMapping("/cartReady")
//     public String readyToCartKakaoPay( int mem_idx, RoomVo roomvo) {

//         Map map = new HashMap();
//         map.put("room_idx", roomvo.getRoom_idx());
//         // 장바구니 결제 목록구하기

//         PaymentParam payment = new PaymentParam();
      
//             payment.getItem_name().add(roomvo.getRoom_name());
//             payment.getP_idx().add(roomvo.getRoom_idx());
//             payment.getPay_count().add(1);
//             payment.getPay_price().add(roomvo.getRoom_price);
//             payment.setMem_idx(mem_idx);
    

//         // p_idx / mem_idx
//         globalPaymentParam = payment;

      

//         KakaoReadyResponse kakaoReadyResponse = kakaoPayService.kakaoPayReady(payment);

       
//         return "redirect:" + kakaoReadyResponse.getNext_redirect_pc_url();
//     }









    //카카오 결제 성공
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        //DB에 저장
        PaymentVo paymentvo = new PaymentVo();

        KakaoPayApprovalVO kavo = new KakaoPayApprovalVO();
        kavo = kakaopay.kakaoPayInfo(pg_token);

        model.addAttribute("info", kavo);


        System.out.println("-----------------------------------------------------------------");
        System.out.println(kavo);
        System.out.println(kavo.getAmount().getTotal());

        int mem_idx = globalPaymentParam.getMem_idx();
        int acc_idx = globalPaymentParam.getAcc_idx();
        int room_idx = globalPaymentParam.getRoom_idx();
        AccVo accvo = accDao.selectOne(acc_idx);
        String acc_name = accvo.getAcc_name();
        String room_check_in = globalPaymentParam.getRoom_check_in();

        model.addAttribute("acc_name", acc_name);
        model.addAttribute("room_check_in", room_check_in);


        paymentvo.setPay_count(1);
        paymentvo.setPay_price(kavo.getAmount().getTotal());
        paymentvo.setPay_type("kakaopay");
        paymentvo.setMem_idx(mem_idx);
        paymentvo.setAcc_idx(acc_idx);
        paymentvo.setRoom_idx(room_idx);


        System.out.println("-----------------------------------------------------------------");
        System.out.println(paymentvo);
        System.out.println(globalPaymentParam);

        
        int res = paymentDao.insert(paymentvo); //success url에서 어떤일이 일어나는지? 상현이한테 Kakaopay 코드도 달라고 하기

        System.out.println(res);

       
        RoomVo roomvo = new RoomVo();
        roomvo.setRoom_idx(room_idx);
        roomvo.setRoom_check_in(room_check_in);
        
        roomDao.changeDate(roomvo);


        return "payment/kakaoPaySuccess";
    }
    
    //결제 전 결제에 필요한 숙소, 방 정보들 보여주기
    @RequestMapping("/roompayment.do")
    public String roompayment(int room_idx, int acc_idx, String room_check_in, Model model){

        RoomVo roomvo =roomDao.selectRoomOne(room_idx);
        AccVo accvo = accDao.selectOne(acc_idx);
        int mem_idx = accvo.getMem_idx();
        MemberVo memvo = memDao.selectOnebyMemberIdx(mem_idx);

        Map<String,Object> map = new HashMap();
        map.put("roomvo", roomvo);
        map.put("accvo", accvo);
        map.put("room_check_in", room_check_in);
        map.put("memvo", memvo);


       model.addAttribute("payment_info", map);
      


        return "payment/roompayment";
    }


//     /**
//      * 결제 성공
//      */
//     @GetMapping("/success")
//     public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model) {

//         KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);   

//         System.out.println("-----[결제결과]-----");

//         // 결제정보를 DB저장
//         int pay_num = paymentDao.selectPayNum();

//             PaymentVo vo = new PaymentVo();

//             vo.setP_idx(globalPaymentParam.getP_idx());      
//             vo.setMem_idx(globalPaymentParam.getMem_idx());

//             // System.out.println(vo.getItem_name());
//             // System.out.println(kakaoApprove);

//             // String item_name = kakaoApprove.getItem_name();
//             String item_name = globalPaymentParam.getItem_name();
//             vo.setItem_name(acc_name);

//             // int Amount = kakaoApprove.getAmount().getTotal();
//             int amount = globalPaymentParam.getPay_price();
//             vo.setPay_price(amount);

//             // int quantity = kakaoApprove.getQuantity();
//             int quantity = globalPaymentParam.getPay_count()
//             vo.setPay_count(1);

//             vo.setPay_type("KAKAOPAY");

//             vo.setPay_num(pay_num);

//             int res = paymentDao.insert(vo);
//             System.out.println(res);


//         model.addAttribute("payment_info", kakaoApprove);
      

//         // 결제 확인 페이지
//         // return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
//         return "payment/payment_success";
//     }





    @RequestMapping("/payment/list.do")
    public String list(Model model) {

        return "payment/payment_list";
    }



//     @PostMapping("/cartReady")
//     public String readyToCartKakaoPay( int mem_idx, RoomVo roomvo) {

//         Map map = new HashMap();
//         map.put("room_idx", roomvo.getRoom_idx());
//         // 장바구니 결제 목록구하기

//         PaymentParam payment = new PaymentParam();
      
//             payment.getItem_name().add(roomvo.getRoom_name());
//             payment.getP_idx().add(roomvo.getRoom_idx());
//             payment.getPay_count().add(1);
//             payment.getPay_price().add(roomvo.getRoom_price);
//             payment.setMem_idx(mem_idx);
    

//         // p_idx / mem_idx
//         globalPaymentParam = payment;

      

//         KakaoReadyResponse kakaoReadyResponse = kakaoPayService.kakaoPayReady(payment);

       
//         return "redirect:" + kakaoReadyResponse.getNext_redirect_pc_url();
//     }

//     /**
//      * 결제 성공
//      */
//     @GetMapping("/success")
//     public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model) {

//         KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);   

//         System.out.println("-----[결제결과]-----");

//         // 결제정보를 DB저장
//         int pay_num = paymentDao.selectPayNum();

//             PaymentVo vo = new PaymentVo();

//             vo.setP_idx(globalPaymentParam.getP_idx());      
//             vo.setMem_idx(globalPaymentParam.getMem_idx());

//             // System.out.println(vo.getItem_name());
//             // System.out.println(kakaoApprove);

//             // String item_name = kakaoApprove.getItem_name();
//             String item_name = globalPaymentParam.getItem_name();
//             vo.setItem_name(acc_name);

//             // int Amount = kakaoApprove.getAmount().getTotal();
//             int amount = globalPaymentParam.getPay_price();
//             vo.setPay_price(amount);

//             // int quantity = kakaoApprove.getQuantity();
//             int quantity = globalPaymentParam.getPay_count()
//             vo.setPay_count(1);

//             vo.setPay_type("KAKAOPAY");

//             vo.setPay_num(pay_num);

//             int res = paymentDao.insert(vo);
//             System.out.println(res);


//         model.addAttribute("payment_info", kakaoApprove);
      

//         // 결제 확인 페이지
//         // return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
//         return "payment/payment_success";
//     }




}