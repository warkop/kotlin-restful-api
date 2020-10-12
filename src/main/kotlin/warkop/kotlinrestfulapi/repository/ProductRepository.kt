package warkop.kotlinrestfulapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import warkop.kotlinrestfulapi.entity.Product

interface ProductRepository : JpaRepository<Product, String>{

}