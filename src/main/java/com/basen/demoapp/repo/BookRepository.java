package com.basen.demoapp.repo;

import com.basen.demoapp.model.BaseEntity;
import com.basen.demoapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    final Map<String, Book> map = new HashMap<>();

    public Book save(final Book newBook) {
        Set <Book> sub = (Set<Book>) newBook.getSubEntities();
        if (sub != null) {
            for (BaseEntity b : sub)
                // create a new entry if does not already exist
                if (null != b && findById(b.getID()).isEmpty()) {
                    Book nb = new Book();
                    nb.setID(b.getID());
                    nb.setSubEntities(b.getSubEntities());
                    nb.setData(b.getData());
                    save(nb);
                }
        }
        return map.putIfAbsent(newBook.getID(), newBook);
    }

    public Optional<Book> findById(final String id) {
        return Optional.ofNullable(map.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(map.values());
    }

    // only update date entry already exists, ignore non-existent entry
    public Book update(final Book updatedBook, final String id) {
        return findById(id).map(book -> {
                    book.setData(updatedBook.getData());
                    book.setSubEntities(updatedBook.getSubEntities());
                    return save(book);
                })
                .orElse(null);
    }

    // naive implementation, will not work for nested entries
    public void deleteById(final String id) {
        map.remove(id);
    }

    public void addDemoData() {
        Book a = new Book();
        Map<String,String> attrs = new HashMap<>();
        attrs.put("name", "Black Swan");
        a.setID("1");
        a.setData(attrs);
        map.put(a.getID(), a);

        Book b = new Book();
        attrs = new HashMap<>();
        attrs.put("name", "Zero To One");
        b.setID("2");
        b.setData(attrs);
        map.put(b.getID(), b);

        Set<Book> subBooks = new HashSet<>();
        subBooks.add(b);
        a.setSubEntities(subBooks);
    }
}
