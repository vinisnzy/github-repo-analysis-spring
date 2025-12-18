package com.vinisnzy.github_repo_analysis.dto.response;

import java.time.LocalDateTime;

public record RepositoryOverviewResponse(
        String name,
        String description,
        String owner,
        int stars,
        int forks,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int openIssues,
        String language) {
}
