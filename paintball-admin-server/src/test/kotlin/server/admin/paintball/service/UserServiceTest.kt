package server.admin.paintball.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.modelmapper.ModelMapper
import org.springframework.security.crypto.password.PasswordEncoder
import server.admin.paintball.dto.request.RegisterUserRequest
import server.admin.paintball.model.Role
import server.admin.paintball.model.User
import server.admin.paintball.repository.UserRepository
import server.admin.paintball.security.authorization.RoleService
import server.admin.paintball.security.authorization.RoleType
import server.admin.paintball.util.exceptions.BadRequestException


@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @InjectMocks
    lateinit var userService: UserServiceImpl

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var passwordEncoder: PasswordEncoder

    @Mock
    lateinit var roleService: RoleService

    @Spy
    lateinit var mapper: ModelMapper

    @Nested
    inner class RegisterUser {

        @Test
        fun `should save new user with correct arguments if it does not exist`() {
            val username = "NewUser"
            val password = "password"
            val role = Role(name = RoleType.PLAYER)

            doReturn(null).`when`(userRepository).findFirstByUsername(anyString())
            doReturn(User()).`when`(userRepository).save(any())
            doReturn(password).`when`(passwordEncoder).encode(password)
            doReturn(role).`when`(roleService).player
            val request = RegisterUserRequest(username, password)

            userService.registerUser(request)

            ArgumentCaptor.forClass(User::class.java).run {
                verify(userRepository).save(capture())
                assertEquals(username, value.username)
                assertEquals(password, value.password)
                assertTrue(value.roles.contains(role))
            }
        }

        @Test
        fun `should throw exception when username already exists`() {
            doReturn(User()).`when`(userRepository).findFirstByUsername(anyString())

            assertThrows(BadRequestException::class.java) {
                userService.registerUser(RegisterUserRequest())
            }
        }
    }
}