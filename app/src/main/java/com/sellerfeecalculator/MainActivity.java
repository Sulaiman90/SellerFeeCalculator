package com.sellerfeecalculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private  Spinner spinner2;
    private double salePrice=0;
    private int categoryPercent=0;
    private double shippingPrice=0;
    private double totalPrice=0;
    private double paisapayPrice=0;
    private double finalValuePrice=0;
    private double totalFee=0;
    private double payoutFee=0;
    EditText saleText;
    EditText shipText;
    TextView paisaText;
    TextView totalFeeText;
    TextView payoutText;
    TextView finalValueText;
    private List<String> l2;
    private int values[][]= {
            {0},
            {1},
            {5},
            {1,1,1,2,2,2,2,2,2,2,2,2,2,5,5,5,5,5},
            {1,2},
            {1,1,1,1,2,2,2,2,2,5,5,5},
            {5,1},
            {1,2,2,2,2,2,2,2,2,2,5,5,5,5,5},
            {1},
            {5,5,1},
            {5,1},
            {7},
            {7},
            {2,2,2,2,2,2,2,2,2,2,5,5,7,7,1},
            {7},
            {10,10,10,10,10,1,1,7,7},
            {7},
            {7},
            {7},
            {2,2,2,2,2,2,7},
            {7},
            {5},
            {7},
            {7},
            {7},
            {6},
            {6},
            {5,7},
            {6},
            {7},
            {7},
            {2,7},
            {7},
            {5}
    };
    private int categoryNo;
    private String items[][]={ {},
            {},{},{"Desktop PCs","LCD/TFT Monitors","Laptops","Panel PCs","Printers","Scanners & Supplies","CD/DVD Drives & Writers",
            "Speakers","Webcams & Multimedia","Networking Equipment","Keyboard & Mouse","Computer Components","Software","Wireless USB Modem",
            "Routers","Laptop Accessories","Internet & Services","PC tools & Accessories"},
            {"iPads & Tablets","Other"},
            {"Digital Cameras", "Film Cameras", "SLRs", "Binoculars","Microscopes", "Camcorders", "Digital Photo Frames",
                    "Telescope", "SLR Camera Lenses","Camera", "Camcorder Accessories", "Other Optics"},
            {"TV Accessories","Other"},
            {"Projectors & Accessories","Apple iPods, MP3 & MP4 Players", "Portable Audio & Video", "DVD & Blu-ray Players", "DVD/ VCD Accessories",
                    "Home Audio", "Head Phones", "Head Sets", "Home Theatre & Accessories", "Landline Phones",
                    "Batteries & Chargers", "Batteries & Chargers", "Apple iPod Accessories", "MP3 Accessories & Everything Else"},
            {},{"Home Security Systems", "Other Home Appliances","Other"},
            {"Gaming Accessories","Other"},
            {},{},
            {"Bath and Spa", "Make Up, Deodorants", "Nail Care & Polish", "Shampoo", "Powder & Talc","Toners & Astringents",
                    "Body and Skin Care", "Hair Care", "Shaving & Hair Removal","Health Care & Instruments",
                    "Perfumes, Massage & Other","Wholesale Lots"},
            {},
            {"Diamond Jewellery", "Loose Diamonds", "Fashion & Imitation Jewellery","Jewellery Storage & Cleaners"
                    ,"Loose Gemstones & Pearls","Precious Metal Coins & Bars", "Gold Jewellery","Sterling Silver Jewellery",
                    "Gemstone Jewellery"},
            {},{},{},
            {"Baby Bath","Grooming","Skin Care","Baby Food & Feeding Items","Baby Gift Packs","Baby Health & Safety","Other"},
            {},{},{},{},{},{},{},
            {"Office Electronics","Other"},
            {},{},{},
            {"FMCG","Other"},{},{}
    };
    String[] mainItems=new String[]{"Select One","Mobile Phones","Mobile Accessories","Laptop & Computer Peripherals","Tablets & Accessories",
            "Cameras & Optics","LCD, LED & Televisions","Audio & Home Entertainment","Memory Cards, Pen Drives & HDD","Home Appliances",
            "Games, Consoles & Accessories","Clothing & Accessories","Shoes","Fragrance, Beauty & Health","Watches",
            "Jewellery & Precious Coins","Home & Living","Kitchenware, Dining & Bar","Toys, Games & School Supplies",
            "Baby & Mom","Coins & Notes","Stamps","Collectibles","Cars & Bike Accessories","Fitness & Sports",
            "Books & Magazines","Movies and music","Stationery & Office Supplies","Tools & Hardware","Musical Instruments",
            "eBay Daily","Everything Else","Cars & Bikes","Warranty Services"
    };
    String[] subItems = new String[]{};
    DecimalFormat pF = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spinner =(Spinner)findViewById(R.id.spinner);
        spinner2 =(Spinner)findViewById(R.id.spinner2);
        saleText= (EditText)findViewById(R.id.salePrice);
        shipText= (EditText)findViewById(R.id.shipPrice);
        finalValueText=(TextView)findViewById(R.id.finalValuePrice);
        paisaText= (TextView)findViewById(R.id.paisaPrice);
        totalFeeText= (TextView)findViewById(R.id.totalFee);
        payoutText= (TextView)findViewById(R.id.payoutFee);

        ArrayAdapter<String> dataAdapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,mainItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
       // spinner.setSelection(Adapter.NO_SELECTION,false);
        saleText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                performCalculations();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        shipText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                performCalculations();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        saleText.setTextColor(Color.BLACK);
        shipText.setTextColor(Color.BLACK);

        shipText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_BACK) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(shipText.getWindowToken(), 0);
                    shipText.setFocusable(false);
                    shipText.setFocusableInTouchMode(true);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {
        //Toast.makeText(parent.getContext(),"Main Category : " + parent.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
        categoryNo=pos;
        resetValues();
        Log.d("Items ","items "+items[categoryNo].length);
        if(items[categoryNo].length==0 && pos!=0){
            spinner2.setVisibility(View.INVISIBLE);
            showValues(pos,0);
        }
        else if(pos!=0){
            spinner2.setVisibility(View.VISIBLE);
            subItems = new String[] {};
            subItems= items[pos];
            ArrayAdapter<String> dataAdapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,subItems);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
            select();
        }
        else if(pos==0){
            spinner2.setVisibility(View.INVISIBLE);
            showValues(pos,0);
        }
    }

    private void showValues(int i,int j){
        categoryPercent = values[i][j];
    }

    private void select() {
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int pos, long arg3) {
                // Toast.makeText(getBaseContext(), "Sub Category "+parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                showValues(categoryNo, pos);
                resetValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void calculateFee(View view){
        performCalculations();
    }

    public void performCalculations(){
      // Log.d("performCalculations","categoryNo"+categoryNo);
        if(saleText.getText().toString().isEmpty()){
            salePrice = 0;
        }
        else{
            salePrice = Double.parseDouble(saleText.getText().toString());
        }

        if(shipText.getText().toString().isEmpty()){
            shippingPrice = 0;
        }
        else{
            shippingPrice = Double.parseDouble(shipText.getText().toString());
        }

        if(categoryNo!=0) {
//            Log.d("SellerFee", "salePrice " + saleText.getText().toString());
//            Log.d("SellerFee", "shipPrice " + shipText.getText().toString());
            totalPrice = salePrice + shippingPrice;
            paisapayPrice = (totalPrice * 4.5) / 100;
            String paisa = pF.format(paisapayPrice);
            paisaText.setText(paisa);
            /* Log.d("paisaPrice", "TP " + paisapayPrice);
            Log.d("category", "TP " + categoryPercent);*/
            finalValuePrice = (totalPrice * categoryPercent) / 100;
           // Log.d("finalValuePrice", "TP " + finalValuePrice);
            if (finalValuePrice < 10 && finalValuePrice > 0) {
                finalValuePrice = 10;
            }
            String fF = pF.format(finalValuePrice);
            finalValueText.setText(fF);
            totalFee = paisapayPrice + finalValuePrice;
            String tF = pF.format(totalFee);
            totalFeeText.setText(tF);
            payoutFee = (salePrice + shippingPrice) - totalFee;
            String pT = pF.format(payoutFee);
            payoutText.setText(pT);
        }
    }

    public void resetValues(){
        Log.d("reset values","rs");
        saleText.setText("");
        shipText.setText("");
        paisaText.setText("");
        finalValueText.setText("");
        totalFeeText.setText("");
        payoutText.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
