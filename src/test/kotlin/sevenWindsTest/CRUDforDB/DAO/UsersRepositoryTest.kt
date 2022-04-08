package sevenWindsTest.CRUDforDB.DAO

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import sevenWindsTest.CRUDforDB.CruDforDbApplication
import sevenWindsTest.CRUDforDB.Entities.Users


@ContextConfiguration(classes = [CruDforDbApplication::class])
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersRepositoryTest {

    @Autowired
    var usersRepository: UsersRepository? = null

    @Autowired
    var testEntityManager: TestEntityManager? = null

    @BeforeEach
    fun createAndSaveUser() {
        testEntityManager!!.persist(Users("johnDow@mail.com", "John Dow", "88888888888"))
        testEntityManager!!.persist(Users("secondUser@mail.com", "secondUser", "88888888889"))
        testEntityManager!!.persist(Users("thirdUser@mail.com", "thirdUser", "88888888887"))
    }

    @Test
    fun findByNameTest() {
        val user: List<Users> = usersRepository!!.findByName("John Dow")
        assertEquals("johnDow@mail.com", user[0].email)
        assertEquals("88888888888", user[0].phone_number)
    }

    @Test
    fun findAllUsersTest() {
        val user = usersRepository!!.findAllUsers()
        assertEquals(3, user.size)
    }
}