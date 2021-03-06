package lt.bit.eshop.controller;

import lt.bit.eshop.ProductNotFound;
import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.service.FIleStorageService;
import lt.bit.eshop.service.ProductService;
import lt.bit.eshop.validation.exceptions.FileFormatException;
import lt.bit.eshop.validation.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FIleStorageService storageService;

    @RequestMapping("/products/create")
    public String productForm(Model model) {

        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("categories", productService.getCategories());

        return "product-form";
    }

    @RequestMapping(value = "/products/edit/{productId}")
    public String editProductForm(@PathVariable Long productId, Model model) {

        model.addAttribute("error", false);
        model.addAttribute("categories", productService.getCategories());
        try {
            model.addAttribute("productModel", productService.getProductById(productId));
        } catch (ProductNotFound e) {
            model.addAttribute("error", true);
            model.addAttribute("message", e.getMessage());
            model.addAttribute("productModel", new ProductModel());
        }

        return "product-form";
    }


    @RequestMapping("/products")
    public String products(@ModelAttribute FilterModel filterModel, Model model) {

        model.addAttribute("productModel", new ProductModel());


        Sort sort = new Sort(Sort.Direction.fromString(filterModel.getSortDirection()), filterModel.getSortBy());
        List<ProductModel> productModelList = this.productService.getAllProducts(filterModel.getName(), sort);

        model.addAttribute("products", productModelList);
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("filterModel", filterModel);

        return "products-list";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute ProductModel productModel, @RequestParam("productImage") MultipartFile file, BindingResult bindingResult, Model model) throws FileFormatException, StorageException, ProductNotFound {

        if (!bindingResult.hasErrors()) {

            Product product = productService.createProduct(productModel);
            model.addAttribute("productModel", new ProductModel());

            String imageName = storageService.store(file, productService.consturctImageName(product.getId()));

            productService.attachImage(product.getId(), imageName);

            return "redirect:products";
        }

        return "product-form";
    }

    @RequestMapping(value = "/categories/create")
    private String categoryForm(Model model) {

        model.addAttribute("categoryModel", new CategoryModel());

        return "admin/create-category";
    }

    @RequestMapping(value = "/categories/create", method = RequestMethod.POST)
    private String createCategory(@Valid @ModelAttribute CategoryModel categoryModel, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            productService.createCategory(categoryModel.getName());

            return "redirect:/admin/categories/create";
        }

        return "admin/create-category";
    }
}
