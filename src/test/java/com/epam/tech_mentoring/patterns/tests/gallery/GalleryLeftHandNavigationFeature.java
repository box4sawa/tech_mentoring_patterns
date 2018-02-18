package com.epam.tech_mentoring.patterns.tests.gallery;


import com.epam.tech_mentoring.patterns.dsl.steps.GalleryPageSteps;
import com.epam.tech_mentoring.patterns.dsl.steps.HomePageSteps;
import com.epam.tech_mentoring.patterns.dsl.steps.InitialSteps;
import com.epam.tech_mentoring.patterns.tests.hooks.FeatureHook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import javax.naming.InitialContext;

import static org.assertj.core.api.Assertions.assertThat;

public class GalleryLeftHandNavigationFeature extends FeatureHook {
    private InitialSteps initialSteps = new InitialSteps(driverManager.getDriver());
    private HomePageSteps homePageSteps = new HomePageSteps(driverManager.getDriver());
    private GalleryPageSteps galleryPageSteps = new GalleryPageSteps(driverManager.getDriver());

    @Test
    public void checkFilterByBrandScenario() {
        initialSteps.navigateToHomePage();
        homePageSteps.navigateToGalleryByLinkText("Мобильные телефоны");
        galleryPageSteps.filterByBrand("Apple");
        galleryPageSteps.verifyAllItemsHaveTheSameBrand("Apple");
    }

    @Test
    public void checkMaxPriceRangeScenario() {
        initialSteps.navigateToHomePage();
        homePageSteps.navigateToGalleryByLinkText("Мобильные телефоны");
        galleryPageSteps.typeMaxPriceRange("3000");
        galleryPageSteps.verifyAllItemsCostLessThan("3000");
    }
}
