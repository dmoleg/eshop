package lt.bit.eshop.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", productService.getAllProducts());

        return "products-list";
    }


    @GetMapping("/{categorySlug}")
    public String products(@PathVariable String categorySlug, Model model) {

        model.addAttribute("categories", productService.getCategories());

        return "products-list";
    }
}
