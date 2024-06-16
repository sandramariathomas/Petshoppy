package com.inzsoft.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.inzsoft.petshopee.bookings.Order;
import com.inzsoft.petshopee.home.Pethome;

public class MainHome extends AppCompatActivity {

    TextView cmp_name,cmp_email,cmp_mob,cmp_adrs,logoutbtn;
    GridView androidGridView;
    String[] gridViewString = {
            "Pet's", "Order List", "Order History", "Chat", "Shop infos",
    } ;
    int[] gridViewImageId = {
            R.drawable.pets, R.drawable.requast, R.drawable.list, R.drawable.chat, R.drawable.company,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getApplicationContext(), gridViewString, gridViewImageId);
        androidGridView=findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                if (i==0)
                {
                    Intent addProduct=new Intent(getApplicationContext(), Pethome.class);
                    startActivity(addProduct);
                }
                if (i==4)
                {


                }
                if (i==3)
                {
                   // Intent ProductList=new Intent(getApplicationContext(),PaymentsView.class);
                   // startActivity(ProductList);
                }
                if (i==2)
                {


                }
                if (i==5)
                {
                   // Intent ProductList=new Intent(getApplicationContext(),Add_Farmer.class);
                   // startActivity(ProductList);
                }
                if (i==1)
                {
                    Intent ProductList=new Intent(getApplicationContext(), Order.class);
                    startActivity(ProductList);

                }
                if (i==7)
                {

                }
            }
        });
    }
}