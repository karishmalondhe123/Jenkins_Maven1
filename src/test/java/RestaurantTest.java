import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantTest {

    Restaurant restaurant;

    @Test

    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        LocalTime openingTime = LocalTime.parse("08:30:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("11:30:00");

        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertTrue(restaurant.isRestaurantOpen());


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        LocalTime openingTime = LocalTime.parse("03:30:00");
        LocalTime closingTime = LocalTime.parse("04:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("09:30:00");


        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void when_returning_order_value_should_be_returned(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        restaurant =new Restaurant("Amelie's Cafe","Thane",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        List<String> menuList = new ArrayList<String>();
        menuList.add("Sweet corn soup");
        menuList.add("Vegetable lasagne");

        System.out.println(restaurant.getItemsOrderValue(menuList));

        assertEquals(388,restaurant.getItemsOrderValue(menuList));


    }
}