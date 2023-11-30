package com.blit.ecommerce.project.service;



import com.blit.ecommerce.project.entities.OrderDetail;
import com.blit.ecommerce.project.entities.Product;
import com.blit.ecommerce.project.exception.FileManagerException;
import com.blit.ecommerce.project.exception.ResourceNotFoundException;

import com.blit.ecommerce.project.repository.OrderRepository;
import com.blit.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private filesOperations fileManager;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No product with id #"+id+"exist"));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }



    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageName(product.getImageName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setSeller(product.getSeller());

        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public String deleteProduct(Long id) {
    List<OrderDetail> orderDetailList = orderRepository.findOrdersByProductId(id);
    if(orderDetailList.isEmpty()){
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
    return "Product cannot be deleted";
    }
    
    	@Override
	public List<Product> regexProducts(String regex) {
		// TODO Auto-generated method stub
		return productRepository.findProductByNameRegex(regex);
	}

    @Override

	public List<Product> findProductByOrderId(Long order_id){
    	return productRepository.findProductByOrderId(order_id);
    }


    /*Update Ramy Ibrahim www.ramy.pro
    * This function will sanitize the input data, upload the image,
    *  and return with the new Product object that will be ready to insert to the database
    * Use this function in controller
    * */
    @Override
    public Product sanatizingProductData(String name, double price, MultipartFile image, String description, String seller, int quantity) throws FileManagerException {

        try {
            if(name.trim().isEmpty())  name = fileManager.generateFileName("product");

            if (price <0) price = 0;

            String imageName = fileManager.upload(image);

            if (seller.trim().isEmpty()) seller = "Seller";
            if(quantity < 0) quantity = 0;


           return new Product(
                    name,
                    price,
                    imageName,
                    description,
                    seller,
                    quantity
            );

        }catch (FileManagerException e){
            throw new FileManagerException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String updateProductImage(Long id, MultipartFile imageFile) throws FileManagerException {

        try {
            Product existingProduct = getProductById(id);
            String imageName = fileManager.upload(imageFile);

            existingProduct.setImageName(imageName);
            productRepository.save(existingProduct);

            return imageName;


        } catch (FileManagerException e){
           throw new FileManagerException(e.getMessage());
        }finally {
            return null;
        }

    }

}
