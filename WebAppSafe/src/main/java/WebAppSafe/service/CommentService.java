package WebAppSafe.service;

import java.util.List;

import WebAppSafe.entity.Comment;

public interface CommentService {
    
    Comment createComment(String username, String text);

    List<Comment> findAll();
}
