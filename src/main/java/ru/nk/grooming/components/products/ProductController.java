package ru.nk.grooming.components.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<Iterable<ProductEntity>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<ProductEntity>> findById(@PathVariable Long id) {
        return functions.responseWithStatus(id, productService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody ProductEntity product,
            HttpServletRequest request
    ) {
        return functions.statusCode(product, productService::save, request);
    }
}
