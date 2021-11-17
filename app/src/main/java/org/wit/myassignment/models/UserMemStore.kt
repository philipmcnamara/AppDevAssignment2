package org.wit.myassignment.models

import timber.log.Timber.i

class UserMemStore : UserStore {

    val users = ArrayList<UserModel>()

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }

    override fun createUser(user: UserModel) {
        user.id = getId()
        users.add(user)
        logAll()
    }

    override fun update(user: UserModel) {
        var foundUser: UserModel? = users.find { p -> p.id == user.id }
        if(foundUser != null){
            foundUser.name = user.name
            logAll()
        }
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        logAll()
    }

    override fun findOne(id: Long): UserModel? {
        var foundUser: UserModel? = users.find { p -> p.id == id }
        return foundUser
    }

    fun logAll() {
        users.forEach{ i("$it") }
    }
    override fun findByEmail(email: UserModel): UserModel? {
        var foundUser: UserModel? = users.find { p -> p.email.equals(email) }
        return foundUser
    }

}