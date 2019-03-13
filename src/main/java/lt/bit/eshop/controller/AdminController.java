package lt.bit.eshop.controller;

import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public String productForm(Model model) {
        model.addAttribute("productModel", new ProductModel());

        return "product-form";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute ProductModel productModel) {

        productService.createProduct(productModel);


        return "product-form";
    }
}
