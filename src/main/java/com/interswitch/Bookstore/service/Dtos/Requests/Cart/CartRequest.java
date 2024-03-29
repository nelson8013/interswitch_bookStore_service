package com.interswitch.Bookstore.service.Dtos.Requests.Cart;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class CartRequest {

   private Long userId;
   private Collection<BookQtyDto> booksAndQuantities;

   public CartRequest(){}

   public CartRequest(Long userId, Collection<BookQtyDto> booksAndQuantities) {
      this.userId             = userId;
      this.booksAndQuantities = booksAndQuantities;
   }


}
