package com.upgrad.mba.services;

import com.upgrad.mba.dao.LanguageDao;
import com.upgrad.mba.entities.Language;
import com.upgrad.mba.exceptions.LanguageDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class LanguageServiceTest {

    @Mock
    private LanguageDao languageDao;

    @InjectMocks
    private LanguageServiceImpl languageService;

    @BeforeEach
    public void setupMockito() {
        Mockito.when(languageDao.save(new Language("save test"))).thenReturn(new Language(1, "save test"));

        Mockito.when(languageDao.findById(1)).thenReturn(Optional.of(new Language(1, "save test")));

        Mockito.when(languageDao.findByLanguageName("save test")).thenReturn(Optional.of(new Language(1, "save test")));
    }

    @Test
    public void testAcceptLanguageDetails() {
        Language language = new Language();
        language.setLanguageName("save test");
        Language savedLanguage = languageService.acceptLanguageDetails(language);

        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("save test", savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetails() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("save test");
        language = languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetails(language.getLanguageId());
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("save test", savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetailsByLanguageName() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("save test");
        languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetailsByLanguageName("save test");
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("save test", savedLanguage.getLanguageName());
    }
}
