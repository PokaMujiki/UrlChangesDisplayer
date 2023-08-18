package ru.softaria;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UrlChangesManagerTest {
    private static final HashSet<String> createdUrls = new HashSet<>();
    private static final HashSet<String> changedUrls = new HashSet<>();
    private static final HashSet<String> deletedUrls = new HashSet<>();

    @BeforeAll
    static void setupStates() {
        createdUrls.addAll(List.of("url 5", "url 6"));
        changedUrls.addAll(List.of("url 3", "url 4"));
        deletedUrls.addAll(List.of("url 1", "url 2"));
    }

    @Test
    void testGenerateReport() {
        var detectorMock = mock(UrlChangeDetector.class);
        var expectedReport = "report";

        when(detectorMock.findCreatedUrls()).thenReturn(createdUrls);
        when(detectorMock.findChangedHtmlUrls()).thenReturn(changedUrls);
        when(detectorMock.findDeletedUrls()).thenReturn(deletedUrls);

        try (MockedStatic<ReportTextCreator> creatorMock = mockStatic(ReportTextCreator.class)){
            creatorMock.when(() -> ReportTextCreator.create(deletedUrls, createdUrls, changedUrls)).thenReturn(expectedReport);

            var manager = new UrlChangesManager(detectorMock);
            var report = manager.generateReport();

            assertEquals(expectedReport, report);

            creatorMock.verify(() -> ReportTextCreator.create(deletedUrls, createdUrls, changedUrls),
                    times(1));
        }

        verify(detectorMock, times(1)).findCreatedUrls();
        verify(detectorMock, times(1)).findChangedHtmlUrls();
        verify(detectorMock, times(1)).findDeletedUrls();
    }
}