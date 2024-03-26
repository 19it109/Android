package com.shivam_patel.clothingecomapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    ImageView logoutBtn;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        logoutBtn = findViewById(R.id.logout);


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_product);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Product list
        productList = new ArrayList<>();

        // Initialize Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        // Retrieve data from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the product list before adding new data
                productList.clear();

                // Iterate through each child node (product) in the database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get product object
                    Product product = snapshot.getValue(Product.class);
                    // Add product to product list
                    productList.add(product);
                }

                // Initialize adapter with product list
                productAdapter = new ProductAdapter(productList);
                // Set adapter for RecyclerView
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(ProductActivity.this, "Failed to retrieve products: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ProductActivity", "Failed to retrieve products: " + databaseError.getMessage());
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(ProductActivity.this,"User logged out",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
