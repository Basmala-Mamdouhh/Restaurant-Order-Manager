package restaurant.Core;
/**
 * IMenuItem represents any item that can appear on the menu.
 * This is the core interface used by both:
 *   - Concrete menu items (MenuItem)
 *   - Decorators (add-ons like extra cheese, sauces...)
 * It enables the Decorator Pattern since decorators wrap objects of this type.
 */
public interface IMenuItem {
    String getName();
    double getPrice();
    String getCategory();
    String getDescription();
}
