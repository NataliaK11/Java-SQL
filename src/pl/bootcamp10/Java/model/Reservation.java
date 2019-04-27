package pl.bootcamp10.Java.model;

import java.util.Date;

public class Reservation {
    private int id;
    private Date startDate;
    private Date endDate;
    private int carId;

    public Reservation(Date startDate, Date endDate, int carId) {
        //this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
