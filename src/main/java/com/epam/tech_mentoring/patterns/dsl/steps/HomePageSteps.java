package com.epam.tech_mentoring.patterns.dsl.steps;

import com.epam.tech_mentoring.patterns.dsl.pages.GalleryPage;
import com.epam.tech_mentoring.patterns.dsl.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageSteps {
    private WebDriver driver;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToGalleryByLinkText(String linkText) {
        HomePage page = new HomePage(driver);
        page.clickOnLinkByText(linkText);
        assertThat(new GalleryPage(driver).isLoaded()).as("Gallery page is not loaded");
    }
}
