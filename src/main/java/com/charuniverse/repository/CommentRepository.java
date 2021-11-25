package com.charuniverse.repository;

import com.charuniverse.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void insert(Comment comment);

    Comment findById(Integer id);

    List<Comment> findAll();

    List<Comment> findByEmail(String email);

}
