package com.interswitch.Bookstore.service.Dtos.Requests.Cart;

import lombok.Data;

@Data
public class BookQtyDto {
   private Long bookId;
   private Long quantity;

   public BookQtyDto(){}

   public BookQtyDto(long bookId, long quantity) {
      this.bookId = bookId;
      this.quantity = quantity;
   }
}
