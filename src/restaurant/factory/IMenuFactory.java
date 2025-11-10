package restaurant.factory;

import java.util.List;
import restaurant.domain.MenuItem;

public interface IMenuFactory {
    List<MenuItem> createMenu();
    MenuItem createItem(String itemKey);
}
