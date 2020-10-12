package warkop.kotlinrestfulapi.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import warkop.kotlinrestfulapi.entity.Product
import warkop.kotlinrestfulapi.error.NotFoundException
import warkop.kotlinrestfulapi.model.CreateProductRequest
import warkop.kotlinrestfulapi.model.ProductResponse
import warkop.kotlinrestfulapi.model.UpdateProductRequest
import warkop.kotlinrestfulapi.repository.ProductRepository
import warkop.kotlinrestfulapi.service.ProductService
import warkop.kotlinrestfulapi.validation.ValidationUtil
import java.util.*

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository,
        val validationUtil: ValidationUtil
): ProductService {
    private fun convertProductToProductResponse(product: Product):ProductResponse{
        return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                createdAt = product.createdAt,
                updatedAt = product.updateAt
        )
    }

    private fun findByIdOrThrowNotFound(id: String): Product{
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        } else {
            return product
        }
    }

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)
        val product = Product(
                id = createProductRequest.id!!,
                name = createProductRequest.name!!,
                price = createProductRequest.price!!,
                quantity = createProductRequest.quantity!!,
                createdAt = Date(),
                updateAt = null
        )

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }
    override fun get(id: String): ProductResponse{
        val product = findByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)

    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findByIdOrThrowNotFound(id)
        validationUtil.validate(updateProductRequest)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updateAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)

    }

    override fun delete(id: String) {
        val product = findByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }


}