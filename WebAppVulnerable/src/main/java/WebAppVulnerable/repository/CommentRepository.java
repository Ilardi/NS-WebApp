package WebAppVulnerable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import WebAppVulnerable.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
