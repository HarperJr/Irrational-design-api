package request

data class PostRequest(
    var artist: String,
    var preview: String,
    var title: String,
    var subtitle: String,
    var description: String,
    var categories: List<String>,
    var tags: List<String>
)