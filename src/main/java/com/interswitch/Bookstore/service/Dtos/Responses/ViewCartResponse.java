package com.interswitch.Bookstore.service.Dtos.Responses;

import com.interswitch.Bookstore.service.Models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewCartResponse {
   private List<Book> books;

   public ViewCartResponse(List<Book> books) {
      this.books = new ArrayList<>(books);
   }
}
