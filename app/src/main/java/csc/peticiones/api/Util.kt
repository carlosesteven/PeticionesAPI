package csc.peticiones.api

import android.util.Log

class Util {
    companion object {

        var DEBUG_MODE = true

        fun consolaDebug(TAG: String?, msg: String?) {

            if (msg != null && msg.isNotEmpty() && TAG != null && TAG.isNotEmpty() && DEBUG_MODE) {
                Log.d("csc_debug", "[ " + TAG.toUpperCase() + " ] -> " + msg)
            }

        }

        fun consolaDebug(TAG: String?, method: String?, msg: String?) {

            if (msg != null && msg.isNotEmpty() && TAG != null && TAG.isNotEmpty() && method != null && method.isNotEmpty() && DEBUG_MODE) {
                Log.d(
                    "csc_debug",
                    "[ " + TAG.toUpperCase() + " ] -> " + method.toUpperCase() + ": " + msg
                )
            }

        }

        fun consolaDebugError(TAG: String?, method: String?, msg: String?) {

            if (msg != null && msg.isNotEmpty() && TAG != null && TAG.isNotEmpty() && method != null && method.isNotEmpty() && DEBUG_MODE) {
                Log.e(
                    "csc_debug",
                    "[ " + TAG.toUpperCase() + " ] -> " + method.toUpperCase() + ": " + msg
                )
            }

        }

        fun consolaDebugError(TAG: String?, msg: String?) {

            if (msg != null && msg.isNotEmpty() && TAG != null && TAG.isNotEmpty() && DEBUG_MODE) {
                Log.e("csc_debug", "[ " + TAG.toUpperCase() + " ] -> " + msg)
            }

        }
    }
}