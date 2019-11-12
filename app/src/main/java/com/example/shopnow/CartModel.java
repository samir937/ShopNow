package com.example.shopnow;

public class CartModel {

        public String name;
        public String totalAmount;
        public String quantity;
        private String id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getTotalAmount()
        {
            return totalAmount;
        }

        public void setTotalAmount(String TotalAmount) {
            this.totalAmount = TotalAmount;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String Quantity) {
            this.quantity = Quantity;
        }




}