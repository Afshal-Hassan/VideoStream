import com.youtube.login.pojos.LoginResponseData


data class LoginResponse(

    private val message: String,
    private val data: LoginResponseData

) {

    fun getMessage(): String {
        return message
    }


    fun getData(): LoginResponseData {
        return data
    }

}
