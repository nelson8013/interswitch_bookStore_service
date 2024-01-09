package com.interswitch.Bookstore.service.Dtos.Requests.Cart;

import lombok.Data;

@Data
public class BookQtyDto {
   private Long bookId;
   private Long quantity;
}
