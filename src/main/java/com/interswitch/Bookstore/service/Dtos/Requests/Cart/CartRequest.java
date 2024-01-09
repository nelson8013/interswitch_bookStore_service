package com.interswitch.Bookstore.service.Dtos.Requests.Cart;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;



@Getter
@Setter
public class CartRequest {
   private Long userId;
   private Collection<BookQtyDto> booksAndQuantities;
}
