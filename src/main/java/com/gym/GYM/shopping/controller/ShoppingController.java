package com.gym.GYM.shopping.controller;

import com.gym.GYM.board.dto.BoardDTO;
import com.gym.GYM.shopping.dto.OrdersDTO;
import com.gym.GYM.shopping.dto.ProductDTO;
import com.gym.GYM.shopping.dto.WishDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gym.GYM.shopping.service.ShoppingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ShoppingController {

    List<WishDTO> wishDTOList = new ArrayList<WishDTO>();
    List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
    List<OrdersDTO> basketList = new ArrayList<OrdersDTO>();
    List<OrdersDTO> basketRegist1 = new ArrayList<OrdersDTO>();
    @Autowired
    private ShoppingService shoppingsvc;

    private ModelAndView mav = new ModelAndView();
    private boolean basketRegi1;


    // shoppionWishFrom : 찜한상품 보기 페이지
    @GetMapping("/shoppingWishForm")
    private ModelAndView shoppingWishForm(@RequestParam String memberId) {
        mav = shoppingsvc.shoppingWishForm(memberId);
        return mav;
    }

    //shoppingList
    @GetMapping("/shoppingMainForm")
    private ModelAndView shoppingList() {
        mav = shoppingsvc.shoppingList();
        return mav;
    }

    // shoppingView
    @GetMapping("/shoppingView")
    private ModelAndView shoppingView(@RequestParam(value = "productCode")String productCode){

        mav = shoppingsvc.shoppingView(productCode);
        return mav;
    }

    //basketRegist: 찜 상품 장바구니에 담고 담겨있는 상품목록 가져오는 메소드
    @PostMapping("/basketRegist")
    private @ResponseBody List<OrdersDTO>basketregist(@RequestParam String productCode, @RequestParam String memberId) {

        basketList = shoppingsvc.basketList(productCode, memberId);
        return basketList;
    }

    //basketForm : 장바구니로 이동하는 메소드

    @GetMapping("/basketForm")
    private String basketForm(){
        return "Shopping/ShoppingBascket";
    }


    //basketView: 내 장바구니 보기
    @GetMapping("/basketView")
    private ModelAndView basketView(@RequestParam String memberId) {

        mav=shoppingsvc.basketView(memberId);
        return mav;
    }

    // shoppingHistory : 주문내역 페이지 이동
    @GetMapping("/shoppingHistory")
    private String shoppingHistory() {

        return "Shopping/shoppingHistory";
    }

    // shoppingBasket : 장바구니 페이지 이동
    @GetMapping("/shoppingBasket")
    private String shoppingBasket() {

        return "Shopping/shoppingBasket";
    }

    // shoppingPayment : 결제 페이지 이동
    @GetMapping("/shoppingPayment")
    private String shoppingPayment() {

        return "Shopping/shoppingPayment";
    }
    //basketInquire :상품이 장바구니에 있는지 확인하는 메소드
    @PostMapping("/basketInquire")
    private @ResponseBody List<String> basketInquire(@RequestParam String memberId, @RequestParam String productCode){


        List<String> basketInquire= new ArrayList<>();
        basketInquire= shoppingsvc.basketInquire(memberId, productCode);

        return basketInquire;
    }

    //basketDelete orders 목록에서 지우기

    @PostMapping("/basketDelete")
    private @ResponseBody List<String>basketDelete(@RequestParam String memberId, @RequestParam String productCode){

        List<String> basketDelete= new ArrayList<>();
        basketDelete=shoppingsvc.basketDelete(memberId,productCode);
        return basketDelete;
    }


    //baskRegist wish 목록에 추가하기

    @PostMapping("/basketRegistAjax")
    private @ResponseBody List<String> basketRegist(@RequestParam String memberId, @RequestParam String productCode){

        List<String> basketInquire= new ArrayList<>();
        basketInquire= shoppingsvc.basketRegistAjax(memberId, productCode);


        return basketInquire;
    }


    //wishInquire :상품이 wish에 있는지 확인하는 메소드

 @PostMapping("/wishInquire")
    private @ResponseBody List<String> wishInquire(@RequestParam String memberId, @RequestParam String productCode){


     List<String> wishInquire= new ArrayList<String>();
     wishInquire= shoppingsvc.wishInquire(memberId, productCode);

        return wishInquire;
 }


    //wishDelete wish 목록에서 지우기

    @PostMapping("/wishDelete")
    private @ResponseBody List<String>wishDelete(@RequestParam String memberId, @RequestParam String productCode){

        List<String> wishDelete= new ArrayList<>();
        wishDelete=shoppingsvc.wishDelete(memberId,productCode);
        return wishDelete;
    }



    //wishregist wish 목록에 추가하기

    @PostMapping("/wishRegist")
    private @ResponseBody List<String> wishregist(@RequestParam String memberId, @RequestParam String productCode){

        List<String> wishInquire= new ArrayList<>();
        wishInquire= shoppingsvc.wishregist(memberId, productCode);


        return wishInquire;
    }

    //basketOrdersPriceUpdate: 장바구니에서 수량 선택시 orderPrice 업데이트 하는 문

    @PostMapping("/basketOrdersPriceUpdate")
    private @ResponseBody List<OrdersDTO> basketOrdersPriceUpdate(@RequestParam String memberId, @RequestParam String productCode,@RequestParam String orderPrice){
        List<OrdersDTO> basketListUpdate = new ArrayList<OrdersDTO>();
        basketListUpdate = shoppingsvc.basketOrdersPriceUpdate(memberId, productCode, orderPrice);
        return basketListUpdate;
    }


    //addressInputAjax : 이전주소 불러오는 ajax

    @GetMapping("/addressInputAjax")
    private @ResponseBody  List<String> addressInputAjax (@RequestParam String memberId){
        List<String> addressInputAjax = new ArrayList<String>();
        addressInputAjax = shoppingsvc.addressInputAjax(memberId);


        return addressInputAjax;
    }

//basketPayment : 모달로 주문시 orders 테이블 업데이트 하는 메소드

    @PostMapping ("/basketPayment")
    private ModelAndView basketPayment(@RequestParam String addr, @RequestParam String coment){
        System.out.println("컨트롤러 요청사항:"+coment);
        System.out.println("컨트롤러 주소:"+addr);
       mav = shoppingsvc.basketPayment(addr, coment);
       return mav;
    }


}
