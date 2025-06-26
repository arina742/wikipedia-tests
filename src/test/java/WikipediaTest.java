
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.MainPage;
import pages.SearchResultsPage;
import static org.junit.jupiter.api.Assertions.*;

public class WikipediaTest extends TestBase {
    private MainPage mainPage;
    @BeforeEach
    public void beforeEach() {
        mainPage = new MainPage().open();
    }

    @Test
    @DisplayName("Проверка, что саджесты начинаются с поискового запроса")
    public void testSearchSuggestionsStartWithQuery() {
        String searchQuery = "Утка";

        mainPage.setSearchValue(searchQuery);

        assertTrue(mainPage.isSuggestionsDisplayed(), "Саджесты должны отображаться");
        assertTrue(mainPage.isFirstSuggestionStartsWith(searchQuery),
                "Первый саджест должен начинаться с поискового запроса");
        assertTrue(mainPage.isSearchQueryBoldInSuggestions(searchQuery),
                "Поисковый запрос должен быть выделен жирным в саджестах");
    }

    @Test
    @DisplayName("Проверка перехода по саджесту на точную страницу")
    public void testNavigationFromSuggestionToExactPage() {
        String searchQuery = "Утка";

        mainPage.setSearchValue(searchQuery);
        String firstSuggestionText = $(".suggestions-results a:first-child").getText();

        mainPage.selectFirstSuggestion();

        SearchResultsPage resultsPage = new SearchResultsPage();
        assertTrue(resultsPage.getPageTitle().contains(firstSuggestionText),
                "Заголовок страницы должен содержать текст саджеста");
    }

    @Test
    @DisplayName("Проверка поведения кнопки поиска при наличии саджестов")
    public void testSearchButtonWithSuggestions() {
        String searchQuery = "Утка";

        mainPage.setSearchValue(searchQuery);
        String firstSuggestionText = $(".suggestions-results a:first-child").getText();

        mainPage.clickSearchButton();

        SearchResultsPage resultsPage = new SearchResultsPage();
        assertTrue(resultsPage.getPageTitle().contains(firstSuggestionText),
                "Заголовок страницы должен содержать текст первого саджеста");
    }

    @Test
    @DisplayName("Проверка поведения кнопки поиска без саджестов")
    public void testSearchButtonWithoutSuggestions() {
        String searchQuery = "Уткацук12";

        mainPage.setSearchValue(searchQuery);
        mainPage.clickSearchButton();
        SearchResultsPage resultsPage = new SearchResultsPage();
        assertTrue(resultsPage.isResultsForQueryDisplayed(searchQuery),
                "Должна отобразиться страница результатов поиска");
    }

    @Test
    @DisplayName("Проверка ссылки 'Поиск страниц содержащих'")
    public void testSearchContainingPagesLink() {
        String searchQuery = "Утка";
        mainPage.setSearchValue(searchQuery);
        mainPage.clickSearchContainingPagesLink();

        SearchResultsPage resultsPage = new SearchResultsPage();
        assertTrue(resultsPage.isResultsForQueryDisplayed(searchQuery),
                "Должна отобразиться страница результатов поиска");
    }

    @Test
    @DisplayName("Ручной тест - Проверка алгоритма ранжирования саджестов")
    public void testSuggestionRelevance() {
        fail("Не автоматизирован - требует субъективной оценки релевантности саджестов, " +
                "что невозможно автоматизировать без четких критериев оценки");
    }
}

