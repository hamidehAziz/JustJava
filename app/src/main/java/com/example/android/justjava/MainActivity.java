package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 98;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else{
            Toast toast = Toast.makeText(getApplicationContext(), "You cannot add more than 100 coffees", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 0){quantity = quantity - 1;
            displayQuantity(quantity);}
         else{
            Toast toast = Toast.makeText(getApplicationContext(), "You cannot add less than 0 coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice();

        // Display the order summary on the screen
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate);

        //Send the Order summary to the email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order For " + getName());
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method gets the input string from user
     * @return
     */

    private String getName(){
        EditText customerName = (EditText) findViewById(R.id.name_field);
        String inputValue = customerName.getText().toString();
        return inputValue;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        int price = 0;
        price =  quantity * 5;

//        // Figure out if the user wants whipped cream topping
//        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
//        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
//
//        // Figure out if the user wants chocolate topping
//        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
//        boolean hasChocolate = chocolateCheckBox.isChecked();
//
//        if (hasWhippedCream){
//            price =  (quantity * (5+1));}
//        else if (hasChocolate){
//            price =  (quantity * (5+2));}
//        else if (hasChocolate && hasWhippedCream){
//            price =  (quantity * (5+2+1));}

        return price;
    }


    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name:" + getName();
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }



}