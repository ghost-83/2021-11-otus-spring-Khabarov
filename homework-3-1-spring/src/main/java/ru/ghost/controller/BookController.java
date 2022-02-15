package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;
import ru.ghost.service.AuthorService;
import ru.ghost.service.BookService;
import ru.ghost.service.CommentService;
import ru.ghost.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @GetMapping({"/", "/books"})
    public String finAll(Model model) {
        List<Book> books = service.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        Book book = id > 0 ? service.findById(id) : new Book();
        model.addAttribute("book", book);

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        List<Comment> comments = id > 0 ? commentService.findAllByBookId(id) : new ArrayList<>();
        model.addAttribute("comments", comments);

        model.addAttribute("comment", new Comment(null, null, book));

        return "book";
    }

    @PostMapping("/books/edit")
    public String save(Book book) {
        service.save(book);
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String delete(@RequestParam("id") Long id) {
        service.delete(id);
        return "redirect:/books";
    }
}
