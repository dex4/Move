package com.pose.move.feature.home.availablescooters

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AvailableScootersViewModel @Inject constructor() : ViewModel() {

    private val _scooters = MutableStateFlow(mutableStateListOf<AvailableScootersListItem>().apply { addAll(itemsList) })
    val scooters: StateFlow<SnapshotStateList<AvailableScootersListItem>>
        get() = _scooters

    fun changeRevealState(scooterId: Int, newRevealState: Boolean) {
        _scooters.update {
            val scootersList = mutableListOf<AvailableScootersListItem>()
            scooters.value.toCollection(scootersList)
            val updatedScooter = (scootersList.first { it.id == scooterId } as AvailableScootersListItem.Scooter).copy(isRevealed = newRevealState)
            scootersList.removeAt(1)
            scootersList.add(1, updatedScooter)
            scootersList.toMutableStateList()
        }
    }

    fun reportIssue(scooterId: Int) {
        Log.d("WRKR", "Issue found with scooter $scooterId")
    }

    companion object {
        private val itemsList: List<AvailableScootersListItem>
            get() {
                val items = mutableListOf<AvailableScootersListItem>()
                ('A'..'O').forEach { letter ->
                    items.add(AvailableScootersListItem.Header(letter))
                    items.addAll(
                        (0..4).map { index ->
                            val id = (letter - 'A') * 10 + index
                            AvailableScootersListItem.Scooter(
                                id,
                                "$letter",
                                "Str. Alverna, nr. 17",
                                65,
                                "#$letter${letter + 1}${if (id < 10) "0$id" else id}"
                            )
                        }
                    )
                }
                return items
            }
    }
}