package org.semenov.votingrest.to;

public class RestaurantTo {

    private Integer id;
    private String description;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
