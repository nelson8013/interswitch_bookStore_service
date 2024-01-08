package com.interswitch.Bookstore.service.Dtos.Responses;

import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.BookCartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewCartResponse {
   private List<BookCartItem> bookItems;

   public ViewCartResponse(List<BookCartItem> bookItems) {
      this.bookItems = new ArrayList<>(bookItems);
   }
}
