package com.irrational.database.document

import org.litote.kmongo.Id

data class Complaint(
    var post: Id<Post>,
    var initiator: Id<Artist>
) : Document<Complaint>()