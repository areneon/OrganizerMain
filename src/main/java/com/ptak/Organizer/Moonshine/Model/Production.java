package com.ptak.Organizer.Moonshine.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Production {
    private Long id;
    private double liters;
    private LocalDate date;

    public Production() {
    }
}
