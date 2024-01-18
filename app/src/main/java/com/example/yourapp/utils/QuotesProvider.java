package com.example.yourapp.utils;

import java.util.Random;

public class QuotesProvider {
    private String[] quotes = {
            "A mother's love is endless, and her wisdom knows no bounds",
            "Motherhood: All love begins and ends there",
            "A mother is your first friend, your best friend, your forever friend",
            "The influence of a mother in the lives of her children is beyond calculation",
            "A mother's hug lasts long after she lets go",
            "Motherhood is the exquisite inconvenience of being another person's everything",
            "A mother's love is the fuel that enables a normal human being to do the impossible",
            "Mothers hold their children's hands for a short while, but their hearts forever",
            "A mother understands what a child does not say",
            "The art of mothering is to teach the art of living to children",
    };

    public String getRandomQuote() {
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        return quotes[randomIndex];
    }
}
