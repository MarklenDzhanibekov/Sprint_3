package model;

public class OrderModelWithBuilder {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }



    public static class Builder {
        private OrderModelWithBuilder newOrderModelWithBuilder;

        public  Builder () {
            newOrderModelWithBuilder = new OrderModelWithBuilder();
        }

        public Builder withFirstName (String firstName) {
            newOrderModelWithBuilder.firstName = firstName;
            return this;
        }

        public Builder withLastName (String lastName) {
            newOrderModelWithBuilder.lastName = lastName;
            return this;
        }

        public Builder withAddress (String address) {
            newOrderModelWithBuilder.address = address;
            return this;
        }

        public Builder withMetroStation (String metroStation) {
            newOrderModelWithBuilder.metroStation = metroStation;
            return this;
        }

        public Builder withPhone (String phone) {
            newOrderModelWithBuilder.phone = phone;
            return this;
        }

        public Builder withRentTime (int rentTime) {
            newOrderModelWithBuilder.rentTime = rentTime;
            return this;
        }

        public Builder withDeliveryDate (String deliveryDate) {
            newOrderModelWithBuilder.deliveryDate = deliveryDate;
            return this;
        }


        public Builder withComment (String comment) {
            newOrderModelWithBuilder.comment = comment;
            return this;
        }

        public Builder withColor (String[] color) {
            newOrderModelWithBuilder.color = color;
            return this;
        }

        public OrderModelWithBuilder build() {
            return newOrderModelWithBuilder;
        }

    }
}
