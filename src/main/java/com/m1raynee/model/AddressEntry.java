package com.m1raynee.model;

public record AddressEntry(String city, String street, String house, int floor) {
    @Override
    public String toString() {
        return String.format("%s, %s, д.%s (%s этаж.)", city, street, house, floor);
    }
}
