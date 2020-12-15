package ru.geekbrains.boot.Homework4.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.Homework4.model.Product;
import ru.geekbrains.boot.Homework4.repositories.ProductInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductInMemoryRepository productInMemoryRepository;

    public List<Product> getProduct() {
        return productInMemoryRepository.getProduct();
    }

    public Optional<Product> getById(Long id) {
        return productInMemoryRepository.getById(id);
    }

    public void removeById(Long id) {
        productInMemoryRepository.removeById (id);
    }

    public Product saveOrUpdate(Product product) {
        return productInMemoryRepository.saveOrUpdate(product);
    }


    //-- фильтры цены
    public List<Product> getProduct(Integer minPrice, Integer maxPrice) {
        List<Product> out = getProduct();
        if (minPrice != null) {
            out = out.stream().filter(product -> product.getPrice () >= minPrice).collect(Collectors.toList());
        }
        if (maxPrice != null) {
            out = out.stream().filter(product -> product.getPrice () >= maxPrice).collect(Collectors.toList());
        }
        return out;
    }

//    public int calculatePrices() {
//        int avg = 0;
//        if (productInMemoryRepository.getProduct().size() == 0) {
//            return 0;
//        } else {
//            return avg = productInMemoryRepository.calculatePrices();
//        }
//    }

}
