package com.charuniverse;

import com.charuniverse.entity.Comment;
import com.charuniverse.repository.CommentRepository;
import com.charuniverse.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("test@test.com", "test cooment");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(4203);

        Assertions.assertNotNull(comment);
        System.out.println(comment);

        Comment notFound = commentRepository.findById(0);

        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();

        Assertions.assertNotNull(comments);
        System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findByEmail("test@test.com");

        Assertions.assertNotNull(comments);
        System.out.println(comments.size());

        List<Comment> notFound = commentRepository.findByEmail("notfound@test.com");

        Assertions.assertEquals(0, notFound.size());
    }

}
