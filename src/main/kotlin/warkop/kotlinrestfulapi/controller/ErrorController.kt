package warkop.kotlinrestfulapi.controller

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import warkop.kotlinrestfulapi.error.NotFoundException
import warkop.kotlinrestfulapi.model.WebResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String>{
        return WebResponse(
                code = 400,
                status = "Bad Request",
                data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String>{
        return WebResponse(
                code = 404,
                status = "NOT FOUND",
                data = "Not Found"
        )
    }
}