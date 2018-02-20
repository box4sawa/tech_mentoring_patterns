package com.epam.tech_mentoring.patterns.dsl.steps;

import com.epam.tech_mentoring.patterns.core.page_factory.PageBuilder;
import com.epam.tech_mentoring.patterns.dsl.pages.GalleryPage;
import com.epam.tech_mentoring.patterns.dsl.pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageSteps {

    public void navigateToHomePage() {
        PageBuilder.build(HomePage.class).get();
    }

    public void navigateToGalleryByLinkText(String linkText) {
        HomePage page = PageBuilder.build(HomePage.class);
        page.clickOnLinkByText(linkText);
        assertThat(PageBuilder.build(GalleryPage.class).isLoaded()).as("Gallery page is not loaded");
    }
}
