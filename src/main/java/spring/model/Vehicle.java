package spring.model;

public class Vehicle {
    String color;
    int year;
    BuildDates buildDates;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BuildDates getBuildDates() {
        return buildDates;
    }

    public void setBuildDates(BuildDates buildDates) {
        this.buildDates = buildDates;
    }
}
