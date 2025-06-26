package pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;


public class MainPage{

    private final SelenideElement searchInput = $("#searchInput");
    private final SelenideElement searchButton = $("#searchButton");
    private final SelenideElement searchSuggestion = $(".suggestions-results");
    private final SelenideElement searchContainingPagesLink = $(".search-contains-pages");

    public MainPage open() {
        Selenide.open("https://www.wikipedia.org");
        return this;
    }

    public MainPage setSearchValue(String value) {
        searchInput.setValue(value);
        return this;
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickSearchContainingPagesLink() {
        searchContainingPagesLink.click();
    }

    public boolean isSuggestionsDisplayed() {
        return searchSuggestion.isDisplayed();
    }

    public boolean isFirstSuggestionStartsWith(String text) {
        return $(".suggestions-results a:first-child").getText().startsWith(text);
    }

    public boolean isSearchQueryBoldInSuggestions(String query) {
        return $(".suggestions-results a:first-child b").getText().equals(query);
    }

    public void selectFirstSuggestion() {
        $(".suggestions-results a:first-child").click();
    }
}