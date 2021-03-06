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

    int quantity = 0;

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
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_for_more_than_100), Toast.LENGTH_SHORT);
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
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_for_less_than_0), Toast.LENGTH_SHORT);
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
        int price = calculatePrice(hasChocolate,hasWhippedCream);

        // Display the order summary on the screen
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate);

        //Send the Order summary to the email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject) + getName());
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
     * @param withChocolate is whether or not the user wants chocolate
     * @param withWhippedCream is whether or not the user wants whipped cream
     * @return total price
     */
    private int calculatePrice(boolean withChocolate, boolean withWhippedCream) {
        int basePrice = 5;

        if(withChocolate){
            basePrice = basePrice + 2;
        }

        if(withWhippedCream){
            basePrice = basePrice + 1;
        }
        return  quantity * basePrice;
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
        String priceMessage = getString(R.string.name_summary) + getName();
        priceMessage += "\n" + getString(R.string.add_whipped_cream)+ addWhippedCream;
        priceMessage += "\n" +getString(R.string.add_chocolate) + addChocolate;
        priceMessage += "\n" +getString(R.string.quantity) + quantity;
        priceMessage += "\n" +getString(R.string.total) + price;
        priceMessage += "\n" +getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}