package com.app.lab2_jsp_tur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.ProductModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private List<ProductModel> products = new ArrayList<>(
            Arrays.asList(
                    new ProductModel("P001", "Product 1", "Desd 1"),
                    new ProductModel("P002", "Product 2", "Desd 2"),
                    new ProductModel("P003", "Product 3", "Desd 3")));

    @GetMapping
    public List<ProductModel> getProductList() {
        return this.products;
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable String id) {
        ProductModel product = this.products.stream()
                .filter((p) -> p.getId().equals(id)).findFirst().orElse(null);

        return product;
    }

    @PostMapping
    public String createProduct(@RequestBody ProductModel product) {
        this.products.add(product);

        return "Created product success";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody ProductModel product) {
        for (int i = 0; i < this.products.size(); i += 1) {
            if (this.products.get(i).getId().equals(id)) {
                this.products.set(i, product);
                return "Updated product success";
            }
        }

        return "Failed update product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        boolean isDeleted = this.products.removeIf((p) -> p.getId().equals(id));

        if (isDeleted) {
            return "Deleted product success";
        } else {
            return "Failed delete product";
        }
    }

}
