package sevenWindsTest.CRUDforDB.Services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import sevenWindsTest.CRUDforDB.DAO.UsersRepository
import sevenWindsTest.CRUDforDB.Entities.Users

@Service
class UserService(private val usersRepository: UsersRepository) {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun addUser(users: Users) {
        usersRepository.save(users)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun findAll(): List<Users> {
        return usersRepository.findAllUsers()
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun findByName(name: String): List<Users> {
        return usersRepository.findByName(name)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun deleteById(id: Long) {
        usersRepository.deleteById(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun setNameById(id: Long, name: String) {
        usersRepository.setNameById(id, name);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun setEmailById(id: Long, email: String) {
        usersRepository.setEmailById(id, email);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun setNumberById(id: Long, number: String) {
        usersRepository.setNumberById(id, number);
    }
}