package com.wilkom.housingapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.wilkom.housingapi.model.HousingLocation;

public class HousingLocationService {
        static List<HousingLocation> dumpLocations = Arrays.asList(
                        new HousingLocation(5000, "Acme Fresh Start Housing", "Chicago", "IL",
                                        "../assets/housing-1.jpg", true,
                                        false),
                        new HousingLocation(5001, "A113 Transitional Housing", "Santa Monica", "CA",
                                        "../assets/housing-2.jpg",
                                        false, false),
                        new HousingLocation(5002, "Warm Beds Housing Support", "Juneau", "AK",
                                        "../assets/housing-3.jpg", true,
                                        true),
                        new HousingLocation(5003, "Sunrise penthouse XXl", "Nukafu", "TG", "../assets/housing-2.jpg",
                                        true, false),
                        new HousingLocation(5004, "Beautiful 2 stairs house", "Odjewou", "TG",
                                        "../assets/housing-1.jpg", false,
                                        true));

        public List<HousingLocation> getAllHousingLocations() {
                return dumpLocations;
        }

        public HousingLocation getHousingLocationById(Integer id) {
                return getAllHousingLocations().stream().filter(h -> h.getId().equals(id)).findFirst().orElse(null);
        }
}