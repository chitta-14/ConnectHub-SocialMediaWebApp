package org.co.connecthub.repository;

import org.co.connecthub.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
