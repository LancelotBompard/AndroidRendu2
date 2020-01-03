package bompard.lancelot.td2

import android.os.Parcelable
import java.io.Serializable

data class Task (val id: String, val title: String, var description: String = "default" ) : Serializable