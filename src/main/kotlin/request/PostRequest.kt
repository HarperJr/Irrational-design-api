package request

data class PostRequest(
    var title: String,
    var subtitle: String,
    var description: String,
    var categories: List<String>,
    var tags: List<String>
)