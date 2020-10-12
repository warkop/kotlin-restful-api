package warkop.kotlinrestfulapi.service

import warkop.kotlinrestfulapi.model.CreateProductRequest
import warkop.kotlinrestfulapi.model.ProductResponse
import warkop.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun delete(id: String)
}