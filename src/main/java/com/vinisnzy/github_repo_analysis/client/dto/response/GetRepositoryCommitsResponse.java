package com.vinisnzy.github_repo_analysis.client.dto.response;

public record GetRepositoryCommitsResponse(String sha, CommitResponse commit) {
}
