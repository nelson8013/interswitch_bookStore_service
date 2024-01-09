package com.interswitch.Bookstore.service.Utils;

import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.CartBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookHelperMethods {

   public static boolean areAllParamsEmpty(String... params) {
      return Arrays.stream(params).allMatch(String::isEmpty);
   }

   public  static List<Book> combineResults(List<Book> titleResult, List<Book> authorResult, List<Book> yearResult, List<Book> genreResult) {
      List<Book> combinedResult = new ArrayList<>();

      combinedResult.addAll(titleResult);
      combinedResult.addAll(authorResult);
      combinedResult.addAll(yearResult);
      combinedResult.addAll(genreResult);

      return combinedResult;
   }



   public static Double totalAmountOfBooks(List<CartBook> cartBooks){
      return cartBooks.stream().mapToDouble(book -> book.getBook().getPrice() *  book.getQuantity() ).sum();
   }
}
