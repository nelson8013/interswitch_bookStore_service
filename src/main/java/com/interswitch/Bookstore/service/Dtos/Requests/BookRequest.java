package com.interswitch.Bookstore.service.Dtos.Requests;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookRequest {
   private Long id;
   private String title;
   private BookGenre genre;
   private String isbn;
   private String author;
   private String yearOfPublication;
}
