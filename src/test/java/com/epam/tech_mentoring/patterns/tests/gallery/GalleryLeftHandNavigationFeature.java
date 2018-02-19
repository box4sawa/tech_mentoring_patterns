package com.epam.tech_mentoring.patterns.tests.gallery;


import com.epam.tech_mentoring.patterns.dsl.steps.GalleryPageSteps;
import com.epam.tech_mentoring.patterns.dsl.steps.HomePageSteps;
import com.epam.tech_mentoring.patterns.tests.hooks.FeatureHook;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GalleryLeftHandNavigationFeature extends FeatureHook {
    private HomePageSteps homePageSteps = new HomePageSteps();
    private GalleryPageSteps galleryPageSteps = new GalleryPageSteps();

    @Test
    public void checkFilterByBrandScenario() {
        homePageSteps.navigateToHomePage();
        homePageSteps.navigateToGalleryByLinkText("Мобильные телефоны");
        galleryPageSteps.filterByBrand("Apple");
        galleryPageSteps.verifyAllItemsHaveTheSameBrand("Apple");
    }

    @Test
    public void checkMaxPriceRangeScenario() {
        homePageSteps.navigateToHomePage();
        homePageSteps.navigateToGalleryByLinkText("Мобильные телефоны");
        galleryPageSteps.typeMaxPriceRange("3000");
        galleryPageSteps.verifyAllItemsCostLessThan("3000");
    }
}
