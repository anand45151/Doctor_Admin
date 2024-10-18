package com.example.doctora.Model;

public class Hospital {
    private String name;
    private String address;
    private String contactNumber;
    private int imageResourceId;
    private String description; // New field for description

    public Hospital(String name, String address, String contactNumber, int imageResourceId, String description) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.imageResourceId = imageResourceId;
        this.description = description; // Initialize the new field
    }

    // Getters for the fields
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }
    public int getImageResourceId() { return imageResourceId; }
    public String getDescription() { return description; } // New getter for description
}
