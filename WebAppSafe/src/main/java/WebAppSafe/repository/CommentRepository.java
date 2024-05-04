package WebAppSafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import WebAppSafe.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
