package com.sharad.e_commerce.service;

import com.sharad.e_commerce.model.Product;
import com.sharad.e_commerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts() {
        for(Product product : productRepo.findAll())
        System.out.println(product);
        return productRepo.findAll();
    }

    public Product getproductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

         // setting all the configuration of image to image variables of product object
         product.setImageName(imageFile.getOriginalFilename());
         product.setImageType(imageFile.getContentType());
         product.setImageData(imageFile.getBytes());

         // saving the updated product to db
         productRepo.save(product);
         return product;
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {


//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        product.setImageData(imageFile.getBytes());

        Product currproduct =  productRepo.findById(id).orElse(product);
        currproduct.setImageName(imageFile.getOriginalFilename());
        currproduct.setImageType(imageFile.getContentType());
        currproduct.setImageData(imageFile.getBytes());

        product.setImageName(currproduct.getImageName());
        product.setImageType(currproduct.getImageType());
        product.setImageData(currproduct.getImageData());

        return productRepo.save(product);
    }

    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
