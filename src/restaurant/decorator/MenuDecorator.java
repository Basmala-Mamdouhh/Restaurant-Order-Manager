package restaurant.decorator;
import restaurant.domain.IMenuItem;

public abstract class MenuDecorator implements IMenuItem {
    public IMenuItem baseItem;
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
