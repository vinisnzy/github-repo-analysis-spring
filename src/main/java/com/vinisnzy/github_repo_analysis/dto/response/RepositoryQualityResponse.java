package com.vinisnzy.github_repo_analysis.dto.response;

public record RepositoryQualityResponse(
        Boolean hasReadme,
        Boolean hasLicense,
        Boolean hasContributingGuidelines,
        Boolean hasGitIgnore) {
}
