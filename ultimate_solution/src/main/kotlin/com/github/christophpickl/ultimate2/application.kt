package com.github.christophpickl.ultimate2

/*
@Entity
data class Customer(
    val firstName: String,
    val lastName: String,
    @Id @GeneratedValue
    val id: Long = -1)

interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findByLastName(lastName: String): Iterable<Customer>
}

@RestController
class CustomerController(private val repository: CustomerRepository) {

    @GetMapping("/customers")
    fun findAll() = repository.findAll()

    @GetMapping("/customers/{lastName}")
    fun findByLastName(@PathVariable lastName:String)
        = repository.findByLastName(lastName)
}
*/
