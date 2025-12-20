package com.vinisnzy.github_repo_analysis.dto.response;

public record RepositoryIssuesPRsResponse(
    Double averageIssuesPerWeek,
    Double averagePRsPerWeek,
    int totalIssues,
    int totalPRs,
    int openIssues,
    int closedIssues,
    int openPRs,
    int closedPRs) {
}
