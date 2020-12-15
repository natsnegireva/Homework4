package ru.geekbrains.boot.Homework4.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.boot.Homework4.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ProductInMemoryRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<> (Arrays.asList (
                new Product (1L, "tshirt", 150),
                new Product (2L, "shorts", 100),
                new Product (3L, "glasses", 50),
                new Product (4L, "socks", 20),
                new Product (5L, "slaps", 80)
        ));
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId () != null) {
            for (int i = 0; i < products.size (); i++) {
                if (products.get (i).getId ().equals (product.getId ())) {
                    products.set (i, product);
                    return product;
                }
            }
        }
        Long newId = products.stream ().mapToLong (Product::getId).max ().orElse (0L) + 1L;
        product.setId (newId);
        products.add (product);
        return product;
    }

    public List<Product> getProduct() {
        return Collections.unmodifiableList (products);
    }

    public Optional<Product> getById(Long id) {
        return products.stream ().filter (s -> s.getId ().equals (id)).findFirst ();
    }

    public void removeById(Long id) {
        products.removeIf (p -> p.getId ().equals (id));
    }
//
//    public int calculatePrices() {
//        if (products.size () == 0) {
//            return 0;
//        }
//        int avg = 0;
//        for (Product product : products) {
//            avg += product.getPrice ();
//        }
//        //avg /= products.size ();
//        return avg;
//    }
}
