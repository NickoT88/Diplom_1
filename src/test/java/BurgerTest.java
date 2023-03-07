import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BurgerTest {
    Burger burger;
    //Данные для тестов из Database
    Bun bun = new Bun("red bun", 300);
    Ingredient ingredient_0 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
    Ingredient ingredient_1 = new Ingredient(IngredientType.FILLING, "cutlet", 100);

    //стаб для булки
    public Bun getMockedBun(Bun bun) {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bun.name);
        when(bunMock.getPrice()).thenReturn(bun.price);
        return bunMock;
    }

    //стаб для ингридиентов
    public Ingredient getMockedIngredient(Ingredient ingredient) {
        Ingredient ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredient.type);
        when(ingredientMock.getName()).thenReturn(ingredient.name);
        when(ingredientMock.getPrice()).thenReturn(ingredient.price);
        return ingredientMock;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsCheckWithMockData() {
        Bun bunExpected = getMockedBun(bun);
        burger.setBuns(bunExpected);
        Assert.assertEquals(bunExpected, burger.bun);

    }

    @Test
    public void addIngredientCheckWithMockData() {
        Ingredient ingredientExpected = getMockedIngredient(ingredient_0);
        burger.addIngredient(ingredientExpected);
        Assert.assertEquals(ingredientExpected, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientCheckWithMockData() {
        Ingredient ingredient = getMockedIngredient(ingredient_0);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientCheckWithMockData() {
        Ingredient firstIngredient = getMockedIngredient(ingredient_0);
        Ingredient secondIngredient = getMockedIngredient(ingredient_1);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(firstIngredient.name, burger.ingredients.get(1).name);
    }

    @Test
    public void getPriceCheckWithMockData() {
        Bun bunForTest = getMockedBun(bun);
        Ingredient ingredient = getMockedIngredient(ingredient_0);
        burger.setBuns(bunForTest);
        burger.addIngredient(ingredient);
        float actual = burger.getPrice();
        Assert.assertEquals(700, actual, 0);
    }

    @Test
    public void getReceiptCheckWithMockData() {
        Bun bunForTest = getMockedBun(bun);
        Ingredient ingredient = getMockedIngredient(ingredient_0);
        burger.setBuns(bunForTest);
        burger.addIngredient(ingredient);
        String actual = burger.getReceipt();
        String expected = String.format("(==== red bun ====)%n" +
                "= sauce hot sauce =%n" +
                "(==== red bun ====)%n" +
                "%n" +
                "Price: 700,000000%n");
        Assert.assertEquals(expected, actual);
    }

}
