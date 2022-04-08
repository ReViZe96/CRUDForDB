package sevenWindsTest.CRUDforDB.Entities

import lombok.Getter
import lombok.Setter
import javax.persistence.*


@Entity
@Table(name = "users")
open class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long,
    @Getter
    @Setter
    @Column(nullable = false)
    var email: String,
    @Getter
    @Setter
    @Column(nullable = false)
    var name: String,
    @Getter
    @Setter
    @Column(nullable = false)
    var phone_number: String,
) {
    constructor(email: String, name: String, phone_number: String) : this(id = 0, email, name, phone_number)
}