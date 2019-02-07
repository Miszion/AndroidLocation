//Mission Marcus Main Activity Class
//This class specifies how the main look of the first page looks and feels.


package com.example.cs478proj1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int request; // request code
    private String input; // the string input we get back from the address
    private Button addressButton; // our address button
    private Button mapsButton; // the maps button
    private boolean willMapsContinue; // the maps continue boolean will tell us what the maps button does.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButton(); // initialize the buttons
        initializeButton2();
        willMapsContinue = false; // set this to false at first.
        request = 0; // set the request to 0 at first.
        input = ""; // set the input to nothing
    }

    public void initializeButton() {
        addressButton = (Button) findViewById(R.id.addressButton); // set up the address button
        addressButton.setOnClickListener(new ButtonListener()); // set the onClick listener

    }

    public void initializeButton2()
    {
         mapsButton = (Button)findViewById(R.id.otherButton); // set the button up
        mapsButton.setOnClickListener(new ButtonListener()); // set the clickListener up
    }

    private class ButtonListener implements View.OnClickListener
    {

        public void onClick(View myObj)
        {
            if (myObj == addressButton) { // if the address button is clicked.
                startActivityForResult(new Intent(MainActivity.this, AddressEnter.class), request);
                //we start the activity for the result (to see if successful)
            }
            else if (myObj == mapsButton) // if the click is the maps Button..
            {
               if (willMapsContinue) // if the address is correctly input
               {


                   String [] splitter = input.split(" "); // split the string by spaces

                    String addIt = "";

                    for (int x = 0; x < splitter.length; x++) // add a '+' wherever a space is.
                    {
                        addIt = addIt + splitter[x] + "+";
                    }


                    addIt = addIt.substring(0, addIt.length()-1); // exclude the last '+'


                   String geoScheme = "geo:0,0?q=" + addIt; // conform to the geoScheme


                   Uri mapIntentUri = Uri.parse(geoScheme); // parse theURI
                   Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapIntentUri); // make a new intent (pass the new uri)
                   mapIntent.setPackage("com.google.android.apps.maps"); // set the package as the google maps
                   startActivity(mapIntent); // start the maps.
               }
               else
               {
                    // if the address is blank or not entered
                   Toast toastMessage = Toast.makeText(getApplicationContext(), "Please enter an Address", Toast.LENGTH_LONG);
                   toastMessage.show();
               }
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent inter)
    {


        if (requestCode == request) // if the request code is the one we are responding to
        {
            if (resultCode == RESULT_OK)  // if the result is good.
            {
                willMapsContinue = true; // set the boolean info
                input = inter.getStringExtra("TextInfo"); // grab the intent info from the first activity
            }
            else
            {
                willMapsContinue = false; // else we keep it at false.

            }
        }
    }


}
