import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

import org.json.JSONObject
@Serializable
data class Success(@SerialName(value = "success") val success: Boolean)
