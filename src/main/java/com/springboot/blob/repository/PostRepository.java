package com.springboot.blob.repository;

import com.springboot.blob.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//We don't need @Repository here as SimpleJpaRepository implementing JpaRepositoryImplementation interface which extends JpaRepository interface
public interface PostRepository extends JpaRepository<Post,Long> {
}
