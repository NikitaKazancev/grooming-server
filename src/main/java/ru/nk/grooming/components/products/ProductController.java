package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.types.ResponseWithStatus;
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
    public ResponseEntity<ResponseWithStatus<ProductEntity>> findById(@PathVariable Long id) {
        ResponseWithStatus<ProductEntity> response = productService.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody ProductEntity product,
            HttpServletRequest request
    ) {
        StatusCode statusCode = productService.save(product, request);
        return ResponseEntity.status(statusCode.getStatus()).body(statusCode);
    }
}
