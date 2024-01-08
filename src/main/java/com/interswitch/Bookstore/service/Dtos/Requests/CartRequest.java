package com.interswitch.Bookstore.service.Dtos.Requests;

import com.interswitch.Bookstore.service.Models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CartRequest {
   private Long userId;
   private List<Long> bookIds;
   private List<Integer> quantities;
}
