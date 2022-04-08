package sevenWindsTest.CRUDforDB.Controllers

import io.swagger.annotations.ApiOperation
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import sevenWindsTest.CRUDforDB.Entities.Users
import sevenWindsTest.CRUDforDB.Services.UserService

@Controller
@RequestMapping("/user")
class UsersController(private val userService: UserService) {

    @ApiOperation("Вывод главной страницы")
    @GetMapping("/mainPage")
    fun getMainPage(model: Model): String {
        var allUsers = userService.findAll()
        return if (allUsers.isEmpty()) {
            "noUsers"
        } else {
            model.addAttribute("allUsers", allUsers)
            "mainPage"
        }
    }

    @ApiOperation("Вывод страницы с формой для добавления нового пользователя")
    @GetMapping("/add")
    fun getForm(): String {
        return "addUser"
    }

    @ApiOperation("Добавление нового пользователя")
    @PostMapping("/add")
    fun saveUser(
        @RequestParam("email") email: String,
        @RequestParam("name") name: String,
        @RequestParam("phone_number") phone_number: String
    ): String {
        val users = Users(id = 0, email, name, phone_number)
        userService.addUser(users)
        return "redirect:/user/mainPage"
    }

    @ApiOperation("Вывод страницы с формой для поиска пользователя")
    @GetMapping("/find")
    fun getFindForm(): String {
        return "findUser";
    }

    @ApiOperation("Вывод результатов поиска")
    @PostMapping("/find")
    fun findUser(@RequestParam name: String, model: Model): String {
        var foundUsers = userService.findByName(name);
        model.addAttribute("foundUsers", foundUsers)
        return if (foundUsers.isEmpty()) {
            "userNotExist"
        } else {
            "foundUsers"
        }
    }

    @ApiOperation("Удаление пользователя")
    @PostMapping("/deleteCurrent/{id}")
    fun deleteCurr(@PathVariable(value = "id") id: Long): String {
        userService.deleteById(id);
        return "redirect:/user/mainPage"
    }

    @ApiOperation("Изменение ФИО пользователя")
    @PostMapping("/editName/{id}")
    fun editCurr(
        @PathVariable(value = "id") id: Long, @RequestParam(value = "name") name: String
    ): String {
        userService.setNameById(id, name);
        return "redirect:/user/mainPage"
    }

    @ApiOperation("Изменение адреса электронной почты пользователя")
    @PostMapping("/editEmail/{id}")
    fun editEmail(
        @PathVariable(value = "id") id: Long, @RequestParam(value = "email") email: String
    ): String {
        userService.setEmailById(id, email);
        return "redirect:/user/mainPage"
    }

    @ApiOperation("Изменение номера телефона пользователя")
    @PostMapping("/editNumber/{id}")
    fun editNumber(
        @PathVariable(value = "id") id: Long, @RequestParam(value = "phone_number") number: String
    ): String {
        userService.setNumberById(id, number);
        return "redirect:/user/mainPage"
    }

}