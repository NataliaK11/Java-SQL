package pl.bootcamp10.Java.model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String carType;

    public Car( String brand, String model, int year, String carType) {
        //this.id=id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.carType = carType;
    }
    public Car(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year=year;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", carType='" + carType + '\'';
    }

    public Car(String brand, String model, String carType) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
    }
}

