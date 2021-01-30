package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.item.Book;
import jpastudy.jpashop.domain.item.Item;
import jpastudy.jpashop.repository.ItemRepository;
import jpastudy.jpashop.web.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(BookForm form) {
        Item savedItem = itemRepository.findOne(form.getId());
        savedItem.setName(form.getName());
        savedItem.setPrice(form.getPrice());
        savedItem.setStockQuantity(form.getStockQuantity());
        Book book = (Book) savedItem;
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
