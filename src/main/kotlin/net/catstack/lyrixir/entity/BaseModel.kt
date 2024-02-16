package net.catstack.lyrixir.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import java.util.*

@MappedSuperclass
open class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long = 0L

    @CreationTimestamp
    open var created: Date? = null
    @UpdateTimestamp
    open var updated: Date? = null
}