package com.vinisnzy.github_repo_analysis.client.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetRepositoryOverviewResponse(
        String name,
        String description,

        @JsonProperty("owner") UserResponse owner,

        @JsonProperty("stargazers_count") int stars,
        int forks,

        @JsonProperty("created_at") LocalDateTime createdAt,

        @JsonProperty("updated_at") LocalDateTime updatedAt,

        @JsonProperty("open_issues_count") int openIssues,
        String language) {
}
