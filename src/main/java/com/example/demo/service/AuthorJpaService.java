package com.example.demo.service;



import com.example.demo.repository.AuthorRepository;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AuthorJpaService implements AuthorRepository {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Override
    public ArrayList<Author> getAuthors() {
        List<Author> authorList = authorJpaRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>(authorList);
        return authors;
    }

    @Override
    public Author getAuthorById(int authorId) {
        try {
            Author author = authorJpaRepository.findById(authorId).get();
            return author;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Author addAuthor(Author author) {
        authorJpaRepository.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(int authorId, Author author) {
        try {
            Author new_author = authorJpaRepository.findById(authorId).get();
            if (author.getAuthorName() != null)
                new_author.setAuthorName(author.getAuthorName());
            authorJpaRepository.save(new_author);
            return new_author;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        try {
            authorJpaRepository.deleteById(authorId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}