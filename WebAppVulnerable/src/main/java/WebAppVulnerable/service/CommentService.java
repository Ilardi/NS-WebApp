package WebAppVulnerable.service;

import java.util.List;

import WebAppVulnerable.entity.Comment;

public interface CommentService {
    
    Comment createComment(String username, String text);

    List<Comment> findAll();
}
