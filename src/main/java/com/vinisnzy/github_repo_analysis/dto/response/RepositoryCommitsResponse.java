package com.vinisnzy.github_repo_analysis.dto.response;

public record RepositoryCommitsResponse(
        Double averageCommitsPerWeek,
        int totalCommits,
        int contributorsCount,
        String mostActiveContributor,
        Double mostActiveContributorCommitsPercentage) {
}
