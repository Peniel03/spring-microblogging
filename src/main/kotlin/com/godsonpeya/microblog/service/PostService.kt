package com.godsonpeya.microblog.service

import com.godsonpeya.microblog.entity.Post
import com.godsonpeya.microblog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService @Autowired constructor(private var postRepository : PostRepository) {
fun getAllPosts(): List<Post> = postRepository.findAll()
fun getOnePost(id: Long) : Post =
        postRepository.findById(id).orElseThrow {IllegalArgumentException("User with id = $id was not found") }
fun savePost(post : Post): Post = postRepository.save(post)
fun updatePost(id:Long, postInput: Post):Post {
    val postFound = getOnePost(id)
    postFound.content = postInput.content
    postFound.createdAt = postInput.createdAt
    postFound.updatedAt = postInput.updatedAt

  return postRepository.save(postFound)


}

fun deletePost(id: Long):String{
    val postFound = getOnePost(id)
    postRepository.delete(postFound)
    return "Post deleted"
}
}