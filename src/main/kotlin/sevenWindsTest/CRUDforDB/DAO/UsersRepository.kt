package sevenWindsTest.CRUDforDB.DAO

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import sevenWindsTest.CRUDforDB.Entities.Users

@Repository
interface UsersRepository : JpaRepository<Users, Long> {

    fun save(users: Users)

    @Query("select us from Users us")
    fun findAllUsers(): List<Users>

    @Query("select us from Users us where us.name= :name")
    fun findByName(@Param("name") name: String): List<Users>

    @Modifying
    @Query("update Users us set us.name= :name where us.id= :id")
    fun setNameById(@Param("id") id: Long, @Param("name") name: String)

    @Modifying(flushAutomatically = true)
    @Query("update Users us set us.email= :email where us.id= :id")
    fun setEmailById(@Param("id") id: Long, @Param("email") email: String)

    @Modifying(flushAutomatically = true)
    @Query("update Users us set us.phone_number= :number where us.id= :id")
    fun setNumberById(@Param("id") id: Long, @Param("number") number: String)
}