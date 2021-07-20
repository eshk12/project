package com.project.Controllers;

import com.project.Models.Product;
import com.project.Objects.Entities.BasicResponseModel;
import com.project.Persist;
import com.project.Utils.Definitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.List;


@RestController
@Transactional
public class ProductController {
    @Autowired
    private Persist persist;

    @PostConstruct
    public void init() {
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public Product addProduct(@RequestParam String name, @RequestParam int quantity, @RequestParam int price) {
        Product productToAdd = new Product(name, quantity, price, false);
        persist.save(productToAdd);
        return productToAdd;
    }

    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public BasicResponseModel deleteProduct(@RequestParam int id, @RequestParam boolean delete) {
        BasicResponseModel basicResponseModel;
        if (id < 1) {
            basicResponseModel = new BasicResponseModel(Definitions.INVALID_ARGUMENT, Definitions.INVALID_ARGUMENT_MSG);
        } else {
            List<Product> productList = persist.getQuerySession().createQuery("FROM Product WHERE id = :id")
                    .setParameter("id", id)
                    .list();
            if (productList.isEmpty()) {
                basicResponseModel = new BasicResponseModel(Definitions.PRODUCT_NOT_EXISTS, Definitions.PRODUCT_NOT_EXISTS_MSG);
            } else if (productList.size() > 1) {
                basicResponseModel = new BasicResponseModel(Definitions.MULTI_RECORD, Definitions.MULTI_RECORD_MSG);
            } else {
                Product product = productList.get(0);
                product.setDeleted(delete);
                persist.save(product);
                basicResponseModel = new BasicResponseModel(product);
            }
        }
        return basicResponseModel;
    }

    @RequestMapping(value = "/product/getAll", method = RequestMethod.GET)
    public BasicResponseModel getAllProducts() {
        BasicResponseModel basicResponseModel;
        List<Product> productList = persist.getQuerySession().createQuery("FROM Product")
                .list();
        basicResponseModel = new BasicResponseModel(productList);

        return basicResponseModel;
    }

    @RequestMapping(value = "/product/getProduct", method = RequestMethod.GET)
    public BasicResponseModel getProduct(@RequestParam int id) {
        BasicResponseModel basicResponseModel;
        if (id < 1) {
            basicResponseModel = new BasicResponseModel(Definitions.INVALID_ARGUMENT, Definitions.INVALID_ARGUMENT_MSG);
        } else {
            List<Product> productList = persist.getQuerySession().createQuery("FROM Product WHERE id = :id")
                    .setParameter("id", id)
                    .list();
            if (productList.isEmpty()) {
                basicResponseModel = new BasicResponseModel(Definitions.PRODUCT_NOT_EXISTS, Definitions.PRODUCT_NOT_EXISTS_MSG);
            } else if (productList.size() > 1) {
                basicResponseModel = new BasicResponseModel(Definitions.MULTI_RECORD, Definitions.MULTI_RECORD_MSG);
            } else {
                basicResponseModel = new BasicResponseModel(productList);
            }
        }
        return basicResponseModel;
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public BasicResponseModel updateProduct(@ModelAttribute("Product") Product product) {
        BasicResponseModel basicResponseModel;
        if (product.getId() < 1) {
            basicResponseModel = new BasicResponseModel(Definitions.INVALID_ARGUMENT, Definitions.INVALID_ARGUMENT_MSG);
        } else {
            if (product.objectIsEmpty()) {
                basicResponseModel = new BasicResponseModel(Definitions.MISSING_FIELDS, Definitions.MISSING_FIELDS_MSG);
            } else{
                List<Product> productList = persist.getQuerySession().createQuery("FROM Product WHERE id = :id")
                        .setParameter("id", product.getId())
                        .list();
                if (productList.isEmpty()) {
                    basicResponseModel = new BasicResponseModel(Definitions.PRODUCT_NOT_EXISTS, Definitions.PRODUCT_NOT_EXISTS_MSG);
                } else if (productList.size() > 1) {
                    basicResponseModel = new BasicResponseModel(Definitions.MULTI_RECORD, Definitions.MULTI_RECORD_MSG);
                } else {
                    Product oldProduct = persist.loadObject(Product.class, product.getId());
                    oldProduct.setObject(product);
                    persist.save(oldProduct);
                    basicResponseModel = new BasicResponseModel(productList);
                }
            }
        }

        return basicResponseModel;
    }
}
