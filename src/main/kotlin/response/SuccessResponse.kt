package response

data class SuccessResponse(
    override val status: String = "success",
    var message: String
) : StatusResponse()