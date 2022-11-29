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
        _scooters.update { scootersList ->
            // Allow multiple revealed actions
//            val scooterIndex = scootersList.indexOfFirst { it.id == scooterId }
//            val updatedScooter =
//                (scootersList.getOrNull(scooterIndex) as? AvailableScootersListItem.Scooter)?.copy(isRevealed = newRevealState)
//                    ?: return@update scootersList
//            scootersList.removeAt(scooterIndex)
//            scootersList.add(scooterIndex, updatedScooter)
//            scootersList

            // Allow single revealed action
            scootersList.map { scootersListItem ->
                when (scootersListItem) {
                    is AvailableScootersListItem.Scooter ->
                        if (scootersListItem.id == scooterId) {
                            scootersListItem.copy(isRevealed = newRevealState)
                        } else {
                            scootersListItem.copy(isRevealed = false)
                        }
                    else -> scootersListItem
                }
            }.toMutableStateList()
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