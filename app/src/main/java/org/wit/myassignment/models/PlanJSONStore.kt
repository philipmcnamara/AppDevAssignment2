package org.wit.myassignment.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.placemark.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "plans.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<TrainerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlanJSONStore(private val context: Context) : PlanStore {

    var plans = mutableListOf<TrainerModel>()
    var plansList = mutableListOf(plans)

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TrainerModel> {
        logAll()
        return plans
    }

    override fun create(plan: TrainerModel) {
        plan.id = generateRandomId()
        plans.add(plan)
        serialize()
    }

    fun findOne(id: Long) : TrainerModel? {
        var foundPlan: TrainerModel? = plans.find { p -> p.id == id }
        return foundPlan
    }

    override fun update(plan: TrainerModel) {
        var foundPlan = findOne(plan.id!!)
        if (foundPlan != null) {
            foundPlan.title = plan.title
        }
        serialize()
    }

    override fun delete(plan: TrainerModel) {
        plans.remove(plan)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(plans, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        plans = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        plans.forEach { Timber.i("$it") }
    }
}

