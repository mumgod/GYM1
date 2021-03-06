package com.gym.GYM.shopping.service;

import com.gym.GYM.shopping.dto.OrdersDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ShoppingService {


	ModelAndView shoppingWishForm(String memberId);

    ModelAndView shoppingList();

    ModelAndView shoppingView(String productCode);

    ModelAndView basketView(String memberId);

    List<OrdersDTO> basketList(String productCode, String memberId);


    List<String> wishInquire(String memberId, String productCode);

    List<String> wishDelete(String memberId, String productCode);

    List<String> wishregist(String memberId, String productCode);

    List<String> basketInquire(String memberId, String productCode);

    List<String> basketDelete(String memberId, String productCode);

    List<String> basketRegistAjax(String memberId, String productCode);

    List<OrdersDTO> basketOrdersPriceUpdate(String memberId, String productCode, String orderPrice);

    List<String> addressInputAjax(String memberId);

    ModelAndView basketPayment(String addr, String coment);



}
