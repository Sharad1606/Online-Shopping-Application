package com.sharad.e_commerce.controller;


import com.sharad.e_commerce.model.Product;
import com.sharad.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getproductById(@PathVariable int id){
        Product product = productService.getproductById(id);
        if(product != null)
        return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(product,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?>addproduct(@RequestPart Product product,
                                       @RequestPart MultipartFile imageFile){

        try {
            Product currProduct =  productService.addProduct(product, imageFile);
            return new ResponseEntity<>(currProduct,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }
    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int prodId){
        Product currProd = productService.getproductById(prodId);
        byte[] imageFile = currProd.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(currProd.getImageType()))
                .body(imageFile);

    }


    // image contents are coming in header of frontend request........
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) throws IOException {

        Product currProduct = productService.updateProduct(id,product,imageFile);

        if(currProduct != null)
            return new ResponseEntity<>("Product "+currProduct.getName()+"is updsted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id){
        if(productService.getproductById(id) != null) {
            productService.deleteProductById(id);
            return new ResponseEntity<>("Product with id " + id + " is deleted successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No Product found",HttpStatus.BAD_REQUEST);
        }
    }

    // for making more user friendly we are searching by each charcter so rapid hits are happening to server :(
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("New Keywords: "+keyword);
        List<Product> products = productService.searchProducts(keyword);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }




}
