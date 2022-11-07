package com.pose.move.feature.home.availablescooters

sealed class AvailableScootersListItem(open val id: Int) {

    data class Header(
        val letter: Char,
        override val id: Int = 'Z' - letter
    ) : AvailableScootersListItem(id)

    data class Scooter(
        override val id: Int,
        val name: String
    ) : AvailableScootersListItem(id)

    companion object {
        fun AvailableScootersListItem.getAlphabeticalIndex(): Int {
            val letter = when (this) {
                is Header -> letter
                is Scooter -> name.first()
            }

            return letter - 'A'
        }
    }
}