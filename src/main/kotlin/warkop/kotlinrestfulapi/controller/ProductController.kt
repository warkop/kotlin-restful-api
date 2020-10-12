package warkop.kotlinrestfulapi.controller

import org.springframework.web.bind.annotation.*
import warkop.kotlinrestfulapi.model.CreateProductRequest
import warkop.kotlinrestfulapi.model.ProductResponse
import warkop.kotlinrestfulapi.model.UpdateProductRequest
import warkop.kotlinrestfulapi.model.WebResponse
import warkop.kotlinrestfulapi.service.ProductService

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
            value = ["api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @GetMapping(
            value = ["api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @PutMapping(
            value = ["api/products/{idProduct}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("idProduct") id: String, @RequestBody updateProductRequest: UpdateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.update(id, updateProductRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }

    @DeleteMapping(
            value = ["api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = id
        )
    }
}