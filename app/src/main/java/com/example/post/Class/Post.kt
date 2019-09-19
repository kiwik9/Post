package com.example.post.Class

class Post {
    constructor(userId: Int?, id: Int?, title: String?, body: String?) {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }

    var userId: Int? = 0
    var id: Int? = 0
    var title: String? = null
    var body: String? = null
}

