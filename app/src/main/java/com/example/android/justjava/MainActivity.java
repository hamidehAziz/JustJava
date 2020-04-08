package com.example.android.justjava;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.sql.StatementEvent;


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
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
       boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
       //Log.v( "MainActivity" , "has " + hasWhippedCream);
        if(hasWhippedCream){
            displayMassage(" Whipped Cream \n" + createOrderSummary());
        } else
            displayMassage(createOrderSummary());
    }



    /**
     * This method creates the summary of the order
     * @return string summary
     */
    private String createOrderSummary(){
        return " Name:" +
                getName() +
                "\n Quantity: " + quantity +
                "\n Price: $" + calculatePrice() +
                "\n Thank  you!";
    }

    /**
     * This method gets the input string from user
     * @return
     */

    private String getName(){
        EditText customerName = (EditText) findViewById(R.id.name);
        String inputValue = customerName.getText().toString();
        return inputValue;
    }

    /**
     *
     * @return This method calculates the total price of the coffee
     */
    private int calculatePrice(){
        return quantity * 5;
    }

    /**
     * This method display the given message on the screen
     */

    private void displayMassage(String message){
        //displayPrice(R.id.price_text_view);
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment (View view){
          quantity ++;
          display(quantity);
    }

    public void decrement (View view){
        quantity --;
        display(quantity);
    }
}