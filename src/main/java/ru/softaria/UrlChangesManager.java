package ru.softaria;

import java.util.Set;

public class UrlChangesManager {

    private final UrlChangeDetector detector;

    public UrlChangesManager(UrlChangeDetector detector) {
        this.detector = detector;
    }

    public String generateReport() {

        Set<String> changedHtmlUrls = detector.findChangedHtmlUrls();
        Set<String> deletedUrls = detector.findDeletedUrls();
        Set<String> createdUrls = detector.findCreatedUrls();

        return ReportTextCreator.create(deletedUrls, createdUrls, changedHtmlUrls);
    }
}
