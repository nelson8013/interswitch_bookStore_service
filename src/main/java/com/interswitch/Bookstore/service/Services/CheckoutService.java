package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.CartExceptions.CartNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.PaymentExceptions.IllegalPaymentOptionException;
import com.interswitch.Bookstore.service.Exceptions.PaymentExceptions.PaymentAlreadyMadeException;
import com.interswitch.Bookstore.service.Exceptions.UserExceptions.UserNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.CheckoutServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import org.springframework.stereotype.Service;

/**
 * The Checkout service.
 *
 * @author Nelson Ekpenyong
 */
@Service
public class CheckoutService implements CheckoutServiceInterface {

    private final CartService cartService;
    private final UserService userService;
    private final BookInventoryService bookInventoryService;

   /**
    * Instantiates a new Checkout service and injects the CartService and UserService into it.
    *
    * @param cartService          the cart service
    * @param userService          the user service
    * @param bookInventoryService the book inventory service
    */
    public CheckoutService(CartService cartService, UserService userService, BookInventoryService bookInventoryService) {
      this.cartService           = cartService;
      this.userService           = userService;
      this.bookInventoryService  = bookInventoryService;
    }



   /**
    * Initiates the checkout process for a user's cart, setting the payment option
    * and updating the payment status accordingly. The supported payment options are
    * WEB, USSD, and TRANSFER. The checkout process involves simulating the payment
    * process without actual payment gateway integration.
    *
    * @param userId         The ID of the user initiating the checkout.
    * @param paymentOption  The selected payment option (WEB, USSD, TRANSFER).
    * @return               The updated cart after the checkout process.
    * @throws CartNotFoundException          If the cart is not found for the user.
    * @throws IllegalPaymentOptionException  If an unsupported payment option is provided.
    */
    @Override
    public Cart checkout(Long userId, PaymentOptions paymentOption) { //Todo: Add cartId to this payload
      User user = userService.getUser(userId);

      Cart cart = cartService.getCartByUser(user);
      if (user == null ) {
         throw new UserNotFoundException("User not found for this cart");
      }

       if (cart == null ) {
          throw new CartNotFoundException("Cart not found for this user");
       }

      if(cart.getPaymentStatus() == PaymentStatus.SUCCESS){
         throw new PaymentAlreadyMadeException("A payment has already been made for this cart");
      }

      switch (paymentOption) {
         case WEB:
            processWebPayment(cart);
            break;
         case USSD:
            processUSSDPayment(cart);
            break;
         case TRANSFER:
            processTransferPayment(cart);
            break;

         default:
            throw new IllegalPaymentOptionException("Unsupported payment option: " + paymentOption);
      }

      return cartService.saveCart(cart);
   }



   /**
    * Simulates the payment process for a cart using the WEB payment option.
    *
    * @param cart  The cart for which the payment process is simulated.
    */
    @Override
    public void processWebPayment(Cart cart) {
      cart.setPaymentStatus(PaymentStatus.SUCCESS);
      cart.setActive(false);
      updateBookInventoryQuantity(cart);
   }


   /**
    * Simulates the payment process for a cart using the USSD payment option.
    *
    * @param cart  The cart for which the payment process is simulated.
    */
    @Override
    public void processUSSDPayment(Cart cart) {
      cart.setPaymentStatus(PaymentStatus.SUCCESS);
      cart.setActive(false);
      updateBookInventoryQuantity(cart);
   }



   /**
    * Simulates the payment process for a cart using the TRANSFER payment option.
    *
    * @param cart  The cart for which the payment process is simulated.
    */
    @Override
    public void processTransferPayment(Cart cart) {
      cart.setPaymentStatus(PaymentStatus.SUCCESS);
      cart.setActive(false);
      updateBookInventoryQuantity(cart);
   }

   void updateBookInventoryQuantity(Cart cart) {
      for (CartBook cartBook : cart.getCartbooks()) {
         Book book = cartBook.getBook();
         int quantity = cartBook.getQuantity();

         bookInventoryService.updateBookQuantity(book.getId(), bookInventoryService.getBookQuantity(book.getId()) - quantity);
      }
   }
}
