package com.pose.move.feature.home.availablescooters.item

sealed class AvailableScootersListItem(open val id: Int) {

    data class Header(
        val letter: Char,
        override val id: Int = 'Z' - letter
    ) : AvailableScootersListItem(id)

    data class Scooter(
        override val id: Int,
        val modelName: String,
        val address: String,
        val battery: Int,
        val pin: String
    ) : AvailableScootersListItem(id)

    companion object {
        fun AvailableScootersListItem.getAlphabeticalIndex(): Int {
            val letter = when (this) {
                is Header -> letter
                is Scooter -> modelName.first()
            }

            return letter - 'A'
        }
    }
}