package data.event

import ID
import kotlinx.serialization.Serializable

@Serializable
internal abstract class EventData {
    open var id: ID? = null
}