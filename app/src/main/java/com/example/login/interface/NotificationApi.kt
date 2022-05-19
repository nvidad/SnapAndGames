package com.example.login.`interface`

import com.example.login.Constants.Constants.Companion.CONTENT_TYPE
import com.example.login.Constants.Constants.Companion.SERVER_KEY
import com.example.login.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=AAAAOPuDfpw:APA91bGFz1jCed9d43ht7etPTUvFIkf7Vn9kmFNW3KACabUCEZ3IMOpPnabU24wzCFXsLL19T-3iuWhmuMoRyNjRpUwFHkcmTCW3ZghIBLNgx36dcbGiG6JjTjL31k3kcVD0-2Udb2In","Content-type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification:PushNotification
    ): Response<ResponseBody>
}