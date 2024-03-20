package com.shoprite.api.services

import com.shoprite.api.domain.UserName
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class UserService {
    fun getUserNameFromJwt(jwt: Jwt) : UserName{
        val userName = jwt.getClaim<String>("preferred_username")
        return  UserName(userName)
    }
}

