package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.components.products.dto.ProductResponse;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<ProductEntity>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        ProductResponse response = productService.findById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody ProductEntity product,
            HttpServletRequest request
    ) {
        StatusCode statusCode = productService.save(product, request);
        return ResponseEntity.status(statusCode.getStatusCode()).body(statusCode);
    }
}
