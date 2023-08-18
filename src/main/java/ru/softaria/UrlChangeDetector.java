package ru.softaria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UrlChangeDetector {
    private final Map<String, String> urlHtmlBefore;
    private final Map<String, String> urlHtmlAfter;

    public UrlChangeDetector(Map<String, String> urlHtmlBefore, Map<String, String> urlHtmlAfter) {
        this.urlHtmlBefore = new HashMap<>(urlHtmlBefore);
        this.urlHtmlAfter = new HashMap<>(urlHtmlAfter);
    }

    public Set<String> findChangedHtmlUrls() {
        Set<String> possiblyChangedHtmlUrls = new HashSet<>(urlHtmlBefore.keySet());
        possiblyChangedHtmlUrls.retainAll(urlHtmlAfter.keySet());

        return possiblyChangedHtmlUrls
                .stream()
                .filter(url -> (!urlHtmlBefore.get(url).equals(urlHtmlAfter.get(url))))
                .collect(Collectors.toSet());
    }

    public Set<String> findDeletedUrls() {
        Set<String> deletedUrls = new HashSet<>(urlHtmlBefore.keySet());
        deletedUrls.removeAll(urlHtmlAfter.keySet());
        return deletedUrls;
    }

    public Set<String> findCreatedUrls() {
        Set<String> createdUrls = new HashSet<>(urlHtmlAfter.keySet());
        createdUrls.removeAll(urlHtmlBefore.keySet());
        return createdUrls;
    }
}
