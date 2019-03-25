package lt.bit.eshop.controller;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(@ModelAttribute FilterModel filterModel, Model model) {
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("filterModel", filterModel);

        model.addAttribute("products", productService.getAllProducts(filterModel.getName()));

        return "products-list";
    }


    @GetMapping("/{categorySlug}")
    public String products(@PathVariable String categorySlug, @ModelAttribute FilterModel filterModel, Model model) {

        CategoryEntity categoryEntity = productService.findCategory(categorySlug);
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", productService.getCategoryProducts(categoryEntity));

        model.addAttribute("slug", categorySlug);

        return "products-list";
    }
}
