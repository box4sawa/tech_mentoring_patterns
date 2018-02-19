package com.epam.tech_mentoring.patterns.dsl.steps;

import com.epam.tech_mentoring.patterns.core.page_factory.PageBuilder;
import com.epam.tech_mentoring.patterns.dsl.pages.GalleryPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GalleryPageSteps {

    public void filterByBrand(String brandName) {
        PageBuilder.build(GalleryPage.class).filterByBrandName(brandName);
    }

    public void verifyAllItemsHaveTheSameBrand(String brandName) {
        GalleryPage page = PageBuilder.build(GalleryPage.class);
        assertThat(page.itemTitles).allMatch(s -> s.getText().contains(brandName))
                .as("Not every item has " + brandName);
    }

    public void typeMaxPriceRange(String price) {
        GalleryPage page = PageBuilder.build(GalleryPage.class);
        page.typeMaxPriceRange(price);
    }

    public void verifyAllItemsCostLessThan(String price) {
        GalleryPage page = PageBuilder.build(GalleryPage.class);
        List<Integer> prices = page.itemPrices.stream()
                .map(element -> Integer.parseInt(element.getText().replaceAll("\\D+","")))
                .collect(Collectors.toList());
        assertThat(prices).allMatch(s -> s < Integer.parseInt(price))
                .as("Item price more than  " + price);
    }
}
