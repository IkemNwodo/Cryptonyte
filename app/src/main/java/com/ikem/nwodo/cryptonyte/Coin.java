package com.ikem.nwodo.cryptonyte;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Ikem Nwodo
 * @since 3/11/2018
 */

public class Coin implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("iconType")
    @Expose
    private String iconType;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("websiteUrl")
    @Expose
    private String websiteUrl;

    @SerializedName("confirmedSupply")
    @Expose
    private Boolean confirmedSupply;
    @SerializedName("numberOfMarkets")
    @Expose
    private Integer numberOfMarkets;
    @SerializedName("numberOfExchanges")
    @Expose
    private Integer numberOfExchanges;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("volume")
    @Expose
    private Integer volume;
    @SerializedName("marketCap")
    @Expose
    private Integer marketCap;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("circulatingSupply")
    @Expose
    private Integer circulatingSupply;
    @SerializedName("totalSupply")
    @Expose
    private Integer totalSupply;
    @SerializedName("approvedSupply")
    @Expose
    private Boolean approvedSupply;
    @SerializedName("firstSeen")
    @Expose
    private Integer firstSeen;
    @SerializedName("change")
    @Expose
    private Double change;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("history")
    @Expose
    private List<String> history = null;
    @SerializedName("penalty")
    @Expose
    private Boolean penalty;
    public final static Parcelable.Creator<Coin> CREATOR = new Creator<Coin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Coin createFromParcel(Parcel in) {
            return new Coin(in);
        }

        public Coin[] newArray(int size) {
            return (new Coin[size]);
        }

    }
            ;

    protected Coin(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.slug = ((String) in.readValue((String.class.getClassLoader())));
        this.symbol = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.iconType = ((String) in.readValue((String.class.getClassLoader())));
        this.iconUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.websiteUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.confirmedSupply = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.numberOfMarkets = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numberOfExchanges = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.volume = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.marketCap = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.circulatingSupply = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalSupply = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.approvedSupply = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.firstSeen = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.change = ((Double) in.readValue((Double.class.getClassLoader())));
        this.rank = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.history, (java.lang.String.class.getClassLoader()));
        this.penalty = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Coin() {
    }

    /**
     *
     * @param change
     * @param iconUrl
     * @param type
     * @param id
     * @param rank
     * @param description
     * @param name
     * @param approvedSupply
     * @param penalty
     * @param websiteUrl
     * @param iconType
     * @param firstSeen
     * @param marketCap
     * @param symbol
     * @param numberOfMarkets
     * @param history
     * @param numberOfExchanges
     * @param price
     * @param circulatingSupply
     * @param color
     * @param volume
     * @param slug
     * @param totalSupply
     * @param confirmedSupply
     */
    public Coin(Integer id, String slug, String symbol, String name, String description, String color, String iconType, String iconUrl, String websiteUrl, Boolean confirmedSupply, Integer numberOfMarkets, Integer numberOfExchanges, String type, Integer volume, Integer marketCap, String price, Integer circulatingSupply, Integer totalSupply, Boolean approvedSupply, Integer firstSeen, Double change, Integer rank, List<String> history, Boolean penalty) {
        super();
        this.id = id;
        this.slug = slug;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.color = color;
        this.iconType = iconType;
        this.iconUrl = iconUrl;
        this.websiteUrl = websiteUrl;
        this.confirmedSupply = confirmedSupply;
        this.numberOfMarkets = numberOfMarkets;
        this.numberOfExchanges = numberOfExchanges;
        this.type = type;
        this.volume = volume;
        this.marketCap = marketCap;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.approvedSupply = approvedSupply;
        this.firstSeen = firstSeen;
        this.change = change;
        this.rank = rank;
        this.history = history;
        this.penalty = penalty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Boolean getConfirmedSupply() {
        return confirmedSupply;
    }

    public void setConfirmedSupply(Boolean confirmedSupply) {
        this.confirmedSupply = confirmedSupply;
    }

    public Integer getNumberOfMarkets() {
        return numberOfMarkets;
    }

    public void setNumberOfMarkets(Integer numberOfMarkets) {
        this.numberOfMarkets = numberOfMarkets;
    }

    public Integer getNumberOfExchanges() {
        return numberOfExchanges;
    }

    public void setNumberOfExchanges(Integer numberOfExchanges) {
        this.numberOfExchanges = numberOfExchanges;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Integer marketCap) {
        this.marketCap = marketCap;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(Integer circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public Integer getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Integer totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Boolean getApprovedSupply() {
        return approvedSupply;
    }

    public void setApprovedSupply(Boolean approvedSupply) {
        this.approvedSupply = approvedSupply;
    }

    public Integer getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Integer firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(slug);
        dest.writeValue(symbol);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(color);
        dest.writeValue(iconType);
        dest.writeValue(iconUrl);
        dest.writeValue(websiteUrl);
        dest.writeValue(confirmedSupply);
        dest.writeValue(numberOfMarkets);
        dest.writeValue(numberOfExchanges);
        dest.writeValue(type);
        dest.writeValue(volume);
        dest.writeValue(marketCap);
        dest.writeValue(price);
        dest.writeValue(circulatingSupply);
        dest.writeValue(totalSupply);
        dest.writeValue(approvedSupply);
        dest.writeValue(firstSeen);
        dest.writeValue(change);
        dest.writeValue(rank);
        dest.writeList(history);
        dest.writeValue(penalty);
    }

    public int describeContents() {
        return 0;
    }

}