//Mission Marcus
//CS478Project1 AddressEnter Class
// This class specifies how it acts when someone clicks the address button on the mainActivity Page.

package com.example.cs478proj1;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class AddressEnter extends AppCompatActivity {

    private EditText addressBox; // our editText Box.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_enter);

        initializeTextBox(); // we initialize the textBox.

    }

    public void initializeTextBox() {

       addressBox = (EditText) findViewById(R.id.addressBox); // we find the correct addressBox.

        addressBox.setOnEditorActionListener(new BoxListener()); // add an editor listener to it.
    }



    private class BoxListener implements TextView.OnEditorActionListener {

        public boolean onEditorAction(TextView text, int i, KeyEvent key) // when someone with the editor occurs.
        {

            int requestCode;

            if (i == EditorInfo.IME_ACTION_DONE) // if the event is a done event ie. done or enter is clicked.
            {
                String textInput = text.getText().toString(); // get the string text.

                if (textInput.equals("")) // if its blank..
                {
                    requestCode = RESULT_CANCELED; // the resultcode must be canceled.

                }
                else
                {
                   requestCode = RESULT_OK; // the result is okay if anything else is input.

                }

                Intent passIt = new Intent(AddressEnter.this, MainActivity.class); // make a new intent.
                passIt.putExtra("TextInfo", addressBox.getText().toString()); // put the text info as an extra.

                setResult(requestCode, passIt); // set the resultcode and pass the intent we just made to the next class.
                finish(); // finish up the activity


            }


            return false; // return false
        }
    }

}
