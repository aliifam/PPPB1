package com.example.droidcafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Creates the content view, the toolbar, and
     * the floating action button.
     * This method is provided in the Basic Activity template.
     *
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMap();
            }
        });
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return Returns true if one of the defined items was clicked.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_order:
                displayToast(getString(R.string.action_order_message));
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    public void showDonutOrder(View view) {
        showFoodOrder(getString(R.string.donut_order_message));
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    public void showIceCreamOrder(View view) {
        showFoodOrder(getString(R.string.ice_cream_order_message));
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    public void showFroyoOrder(View view) {
        showFoodOrder(getString(R.string.froyo_order_message));
    }

    /**
     * Displays a Toast message for the food order and starts the OrderActivity activity.
     *
     * @param message Message to display.
     */
    public void showFoodOrder(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void displayMap() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        // Using the coordinates for Google headquarters.
        String data = getString(R.string.google_mtv_coord_zoom12);
        intent.setData(Uri.parse(data));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}