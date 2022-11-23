package com.pose.move.util.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val moveDispatchers: MoveDispatchers)

enum class MoveDispatchers {
    IO
}