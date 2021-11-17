package org.wit.myassignment.models

interface UserStore {
    fun findAll(): List<UserModel>
    fun createUser(user: UserModel)
    fun update(user: UserModel)
    fun delete(user: UserModel)
    fun findOne(id: Long): UserModel?
    fun findByEmail(email: UserModel): UserModel?

}