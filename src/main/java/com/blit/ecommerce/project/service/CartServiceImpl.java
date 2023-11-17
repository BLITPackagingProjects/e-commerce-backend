package com.blit.ecommerce.project.service;
import com.blit.ecommerce.project.entities.Cart;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Override
    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Integer cartId, Long productId, int countToAdd) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productService.getProductById(productId);

        cart.getProductList().add(product);
        cart.addCount(countToAdd);
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Integer cartId, Long productId, int countToRemove) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productService.getProductById(productId);

       if(cart!=null && product!=null){
           int currentCount = cart.getNumber();
           int newCount = Math.max(currentCount - countToRemove, 0);
           cart.getProductList().removeIf(p-> p.equals(product));
           cart.setNumber(newCount);
           cartRepository.save(cart);
       }
    }

    @Override
    public List<Product> getAddedProducts(Cart cart) {
        return cart.getProductList();
    }

    @Override
    public double getTotalPrice(Cart cart) {
      return cart.getProductList().stream()
              .mapToDouble(Product::getPrice)
              .sum();
    }

    @Override
    public void clearCart(Cart cart) {
        cart.getProductList().clear();
        cartRepository.save(cart);
    }
}
