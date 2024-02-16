package net.catstack.lyrixir.dto

import net.catstack.lyrixir.exception.BaseServiceException

data class AdapterResponse<T>(val status: Int, val error: MsgError?, val response: T?) {
    companion object {
        fun error(e: BaseServiceException) = AdapterResponse<Any>(0, MsgError(e.getCode(), e.message), null)

        fun<T> success(response: T) = AdapterResponse(1, null, response)
    }
}

data class MsgError(val code: Int, val message: String)
