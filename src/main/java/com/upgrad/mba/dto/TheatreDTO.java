package com.upgrad.mba.dto;

public class TheatreDTO {

        int theatreId;
        String theatreName;
        float ticketPrice;
        int cityId;

        public int getTheatreId() {
            return theatreId;
        }

        public void setTheatreId(int theatreId) {
            this.theatreId = theatreId;
        }

        public String getTheatreName() {
            return theatreName;
        }

        public void setTheatreName(String theatreName) {
            this.theatreName = theatreName;
        }

        public float getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(float ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

}
