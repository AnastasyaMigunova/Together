package com.together.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object Utils {
   fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        val hashInBytes = md.digest(password.toByteArray(StandardCharsets.UTF_8))
        return hashInBytes.joinToString("") { "%02x".format(it) }
    }

    fun Modifier.fillWidthOfParent(parentPadding: Dp) = this.then(
        layout { measurable, constraints ->
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx(),
                ),
            )
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        },
    )
}