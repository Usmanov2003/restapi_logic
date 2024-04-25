package com.example.rest_apis.repository;

import com.example.rest_apis.entity.Category;
import com.example.rest_apis.entity.Post;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByPostTitle(String keyword);

    List<Post> findByPostTitleContaining(String keyword);
}
