package restaurant.CustomAddOns;
import restaurant.Core.IMenuItem;
/**
 * MenuDecorator is the abstract Decorator in the Decorator Pattern.
 * It wraps an IMenuItem object and forwards all method calls to it.
 * Concrete decorators (add-ons) extend this class to add their own behavior.
 */
public abstract class MenuDecorator implements IMenuItem {

    protected IMenuItem baseItem;

    public MenuDecorator(IMenuItem baseItem) {
        this.baseItem = baseItem;
    }

    @Override
    public String getName() {
        return baseItem.getName();
    }

    @Override
    public double getPrice() {
        return baseItem.getPrice();
    }

    @Override
    public String getDescription() {
        return baseItem.getDescription();
    }

    @Override
    public String getCategory() {
        return baseItem.getCategory();
    }
}
