package com.example.finalproject.controller;


import com.example.finalproject.component.FileUploadUtil;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.User;
import com.example.finalproject.service.ProductServiceImpl;
import com.example.finalproject.service.UserService;
import com.example.finalproject.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class productController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(productController.class);

    @GetMapping(value="product-image") // it will be set to be /product
    public String productWithImage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.current_user(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("product", new Product());
        return "product-image";
    }

    @PostMapping(path="product/image/new")
    public String newProductWithImage(@RequestParam(name="name") String name,
                                      @RequestParam(name="description") String description,
                                      @RequestParam(name="price") Integer price,
                                      @RequestParam(name="image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        System.out.println(fileName);
        logger.debug("Hello from Logback {}");

        Product new_product = new Product( name, description, price);
        productService.createOrUpdateProduct(new_product);
        new_product.setImageURL("user-photos/uploads/"+ new_product.getId() + "/" + fileName);
        productService.createOrUpdateProduct(new_product);

        String uploadDir = "src/main/resources/static/user-photos/uploads/" + new_product.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/every-products";
    }

    @GetMapping(value="/individual")
    public String everyproductByIndividual(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.current_user(auth.getName());
        List<Product> list = productService.findAllByUserId(user.getId());
        model.addAttribute("products", list);
        return "individual";
    }

    @GetMapping(value="/every-products")
    public String everyproduct(Model model){
        List<Product> list = productService.getAllProduct();
        model.addAttribute("products", list);
        return "every-products";
    }

    @GetMapping(value="product") // it will be set to be /product
    public String product(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.current_user(auth.getName());

        model.addAttribute("user", user);
        model.addAttribute("product", new Product());
        return "product";
    }

    @GetMapping(path="product/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    List<Product> all() {
        return productService.getAllProduct();
    }

    // this is for form-data
    @PostMapping(path="product/new")
    public String newProduct(@RequestParam(name="name") String name,
                             @RequestParam(name="description") String description,
                             @RequestParam(name="price") Integer price,
                             @RequestParam(name="user_id") Integer user_id) {

        //        System.out.println("------*------");
        //        System.out.println("user_id" + user_id);

        Product new_product = new Product(name, description, price, user_id);
        productService.createOrUpdateProduct(new_product);
        return "redirect:/every-products";
    }

    // This is for Javascript if needed
    @PostMapping(path="product/json/new", produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String newProduct(@RequestBody Product product) {
        productService.createOrUpdateProduct(product);
        return "{\"status\": \"success\"}";
    }

    @RequestMapping(path="product/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public String destroy(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/every-products";
    }

    @GetMapping(path="product/show/{id}")
    public String show(Model model, @PathVariable Integer id) {
        Optional<Product> product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "show";
    }

    @RequestMapping(path = {"product/edit", "product/edit/{id}"})
    public String editProduct(Model model, @PathVariable("id") Integer id)
    {
        if (id != null) { // when id is null, because it is not in the database
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.current_user(auth.getName());
            model.addAttribute("user", user);

            Optional<Product> entity = productService.getProduct(id);
            model.addAttribute("product", entity.get());
        } else { //else id is present, then we will just create a new entry in the database
            model.addAttribute("product", new Product());
        }
        return "product";
    }


}
