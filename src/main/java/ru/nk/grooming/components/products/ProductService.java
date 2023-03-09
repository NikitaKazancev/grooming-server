package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final AuthService authService;

    public StatusCode save(ProductEntity product, HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return StatusCode.create(403);
        }

        ProductEntity dbProduct = findByName(product.getName());
        if (dbProduct != null) {
            return StatusCode.create(409);
        }

        productRepo.save(product);
        return StatusCode.create(200);
    }

    public ProductEntity findByName(String name) {
        return productRepo.findByName(name).orElse(null);
    }

    public ResponseWithStatus<ProductEntity> findById(Long id) {
        Optional<ProductEntity> product = productRepo.findById(id);
        if (product.isPresent()) {
            return ResponseWithStatus.<ProductEntity>builder()
                    .data(product.get())
                    .statusCode(200)
                    .build();
        }

        return ResponseWithStatus.<ProductEntity>builder()
                .data(null)
                .statusCode(404)
                .build();
    }

    public Iterable<ProductEntity> findAll() {
        return productRepo.findAll();
    }
}
