package com.example.post.Class

class Comment {
    constructor(userId: Int?, id: Int?, title: String?,email: String?, body: String?) {
        this.userId = userId
        this.id = id
        this.title = title
        this.email = email
        this.body = body
    }

    var userId: Int? = 0
    var id: Int? = 0
    var title: String? = null
    var body: String? = null
    var email: String? = null
}
