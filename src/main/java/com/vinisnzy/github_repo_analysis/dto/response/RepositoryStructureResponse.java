package com.vinisnzy.github_repo_analysis.dto.response;

import java.util.Map;

public record RepositoryStructureResponse(
        Map<String, Double> languagesPercentage,
        String framework,
        Boolean hasTests) {
}
