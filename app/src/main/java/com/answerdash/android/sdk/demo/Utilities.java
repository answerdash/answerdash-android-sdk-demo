package com.answerdash.android.sdk.demo;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class Utilities {

    private Utilities() {

    }

    @NonNull
    public static List<String> getAvailableCountries() {

        String[] isoCountries = Locale.getISOCountries();
        List<String> countries = new ArrayList<>();
        for (String twoLetterCountryCode : isoCountries) {
            Locale locale = new Locale("", twoLetterCountryCode);
            countries.add(locale.getDisplayCountry());
        }

        Collections.sort(countries);
        return countries;
    }
}
