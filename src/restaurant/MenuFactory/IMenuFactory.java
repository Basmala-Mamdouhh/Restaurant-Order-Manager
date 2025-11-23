package restaurant.MenuFactory;
import java.util.List;

import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;
/**
 * Abstract Factory interface for creating families of related menu items.
 * Each concrete factory (Veg, Non-Veg, Kids) must implement:
 *  - createMenuList(): returns the full list of menu items for that category.
 *  - createItem(): creates a specific item based on a provided key.

 * This allows the client to generate different menu types without
 * depending on concrete implementations.
 */

public interface IMenuFactory {
    List<IMenuItem> createMenuList();
    MenuItem createItem(String itemType);
}
