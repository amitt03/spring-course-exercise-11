package com.sc101.imaggregator.vendor.api;

import java.util.Date;
import java.util.UUID;

/**
 * @author Amit Tal
 * @since 5/6/2014
 */
public class ImQuote {

    private String id;
    private String vendorName;
    private int priceInCent;
    private Date expirationDate;

    public ImQuote() {
        this.id = UUID.randomUUID().toString();
    }

    public ImQuote(String id, String vendorName, int priceInCent, Date expirationDate) {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.vendorName = vendorName;
        this.priceInCent = priceInCent;
        this.expirationDate = expirationDate;
    }

    public ImQuote(String vendorName, int priceInCent, Date expirationDate) {
        this(null, vendorName, priceInCent, expirationDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public void setPriceInCent(int priceInCent) {
        this.priceInCent = priceInCent;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImQuote{");
        sb.append("id='").append(id).append('\'');
        sb.append(", vendorName='").append(vendorName).append('\'');
        sb.append(", priceInCent=").append(priceInCent);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append('}');
        return sb.toString();
    }
}
