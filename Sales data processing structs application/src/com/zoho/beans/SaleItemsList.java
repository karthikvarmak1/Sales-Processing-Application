package com.zoho.beans;

public class SaleItemsList {
	private SaleItems[] saleItems;

    public SaleItems[] getSaleItems ()
    {
        return saleItems;
    }

    public void setSaleItems (SaleItems[] saleItems)
    {
        this.saleItems = saleItems;
    }

    @Override
    public String toString()
    {
        return "SaleItemsList [saleItems = "+saleItems+"]";
    }
}
