package com.akondi.cleanarchitecure.login

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator
@Inject constructor(){
    //Here you should put your own logic to return whether the user
    //is authenticated or not
    fun userLoggedIn() = true
}