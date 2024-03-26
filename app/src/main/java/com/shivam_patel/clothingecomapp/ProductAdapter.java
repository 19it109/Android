package com.shivam_patel.clothingecomapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    // Constructor
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private ImageView productImageView;
        private TextView productPriceTextView;
        private TextView productDescriptionTextView;
        private Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productImageView = itemView.findViewById(R.id.product_image);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productDescriptionTextView = itemView.findViewById(R.id.product_description);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            // Load image into ImageView using a library like Picasso or Glide
            Picasso.get().load(product.getImageUrl()).into(productImageView);
            productPriceTextView.setText(String.valueOf(product.getPrice()));
            productDescriptionTextView.setText(product.getDescription());
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle add to cart button click
                }
            });
        }
    }
}
