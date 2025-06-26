package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {

        private final SelenideElement searchResults = $("#searchResults");

        public String getPageTitle() {
            return title();
        }

        public boolean isResultsForQueryDisplayed(String query) {
            return searchResults.exists() &&
                    searchResults.getText().contains(query);
        }
    }
