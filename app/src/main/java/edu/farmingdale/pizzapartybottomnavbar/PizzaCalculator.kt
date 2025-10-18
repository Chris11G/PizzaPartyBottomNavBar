package edu.farmingdale.pizzapartybottomnavbar   // ← add this line

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

// Add Hungry between Medium(3) and Very hungry(5)
enum class HungerLevel(var numSlices: Int) {
    LIGHT(2),
    MEDIUM(3),
    HUNGRY(4),      // ← new
    VERYHUNGRY(5)
}

class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {
    var partySize = 0
        set(value) { field = if (value >= 0) value else 0 }

    val totalPizzas: Int
        get() = ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()

    init {
        this.partySize = partySize
    }
}
