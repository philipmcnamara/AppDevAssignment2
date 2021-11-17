package org.wit.myassignment.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.placemark.helpers.*
import timber.log.Timber
import timber.log.Timber.i
import java.lang.reflect.Type
import java.util.*

const val USER_JSON_FILE = "users.json"
val user_gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val user_listType: Type = object : TypeToken<ArrayList<UserModel>>() {}.type


 fun generateRandomUserId(): Long {
    return Random().nextLong()
}

class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }

    override fun createUser(user: UserModel) {
        user.id = generateRandomUserId()
        users.add(user)
        serialize()
    }

    override fun findOne(id: Long): UserModel? {
        var foundUser: UserModel? = users.find { p -> p.id == id }
        return foundUser
    }

    override fun findByEmail(email: UserModel): UserModel? {
        TODO("Not yet implemented")
    }

    override fun update(user: UserModel) {
        var foundPlan = findOne(user.id!!)
        if (foundPlan != null) {
            foundPlan.name = user.name
        }
        serialize()
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }

    private fun serialize() {
        val jsonString = user_gsonBuilder.toJson(users, user_listType)
        write(context, USER_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, USER_JSON_FILE)
        users = user_gsonBuilder.fromJson(jsonString, user_listType)
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}

