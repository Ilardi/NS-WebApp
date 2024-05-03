package WebAppVulnerable.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WebAppVulnerable.entity.Comment;
import WebAppVulnerable.repository.CommentRepository;
import WebAppVulnerable.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(String username, String text) {
        
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setComment(text);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
