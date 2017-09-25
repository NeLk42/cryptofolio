package nelk.io.crypton.recyclerview.models;


public class GridItem {

    String name;

    String coinAmount;
    String logoUrl;
    String priceBought;
    String priceNow;
    String percentageChange;
    String totalSpent;
    String totalNow;
    String earnings;

    public GridItem(String name) {
        this.name = name;
    }

    public void setCoinAmount(String coinAmount) {
        this.coinAmount = coinAmount;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setPriceBought(String priceBought) {
        this.priceBought = priceBought;
    }

    public void setPriceNow(String priceNow) {
        this.priceNow = priceNow;
    }

    public void setPercentageChange(String percentageChange) {
        this.percentageChange = percentageChange;
    }

    public void setTotalSpent(String totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setTotalNow(String totalNow) {
        this.totalNow = totalNow;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getName() {
        return name;
    }

    public String getCoinAmount() {
        return coinAmount;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getPriceBought() {
        return priceBought;
    }

    public String getPriceNow() {
        return priceNow;
    }

    public String getPercentageChange() {
        return percentageChange;
    }

    public String getTotalSpent() {
        return totalSpent;
    }

    public String getTotalNow() {
        return totalNow;
    }

    public String getEarnings() {
        return earnings;
    }
}
