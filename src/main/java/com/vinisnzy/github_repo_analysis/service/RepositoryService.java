package com.vinisnzy.github_repo_analysis.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vinisnzy.github_repo_analysis.client.GithubClient;
import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryCommitsResponse;
import com.vinisnzy.github_repo_analysis.dto.request.RepositoryRequest;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryCommitsResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryIssuesPRsResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryOverviewResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryQualityResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepositoryService {

  private final GithubClient githubClient;

  public RepositoryCommitsResponse getRepositoryCommits(RepositoryRequest data) {
    List<GetRepositoryCommitsResponse> repoCommits = githubClient.getRepositoryCommits(data.owner(), data.repository(),
        LocalDate.now().minusDays(7).toString());

    Double averageCommitsPerWeek = repoCommits.size() / 7.0;
    Integer totalCommits = repoCommits.size();

    Map<String, Integer> contributorsCount = new HashMap<>();
    repoCommits.forEach(commit -> {
      String committer = commit.commiter().login();
      contributorsCount.put(committer, contributorsCount.getOrDefault(committer, 0) + 1);
    });

    String mostActiveContributor = contributorsCount.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);

    Double mostActiveContributorCommitsPercentage = mostActiveContributor != null
        ? (contributorsCount.get(mostActiveContributor) / (double) totalCommits) * 100
        : 0.0;

    return new RepositoryCommitsResponse(averageCommitsPerWeek, totalCommits, contributorsCount.size(),
        mostActiveContributor, mostActiveContributorCommitsPercentage);
  }

  public RepositoryIssuesPRsResponse getRepositoryIssuesPRs(RepositoryRequest data) {
    var repoIssues = githubClient.getRepositoryIssues(data.owner(), data.repository(), "all",
        LocalDate.now().minusDays(7).toString());

    Double averageIssuesPerWeek = repoIssues.size() / 7.0;
    Integer totalIssues = repoIssues.size();
    Integer openIssues = (int) repoIssues.stream().filter(issue -> issue.state().equals("open")).count();
    Integer closedIssues = totalIssues - openIssues;

    var repoPRs = githubClient.getRepositoryPRs(data.owner(), data.repository(), "all",
        LocalDate.now().minusDays(7).toString());

    Integer totalPRs = repoPRs.size();
    Double averagePRsPerWeek = totalPRs / 7.0;
    Integer openPRs = (int) repoPRs.stream().filter(pr -> pr.state().equals("open")).count();
    Integer closedPRs = totalPRs - openPRs;

    return new RepositoryIssuesPRsResponse(
        averageIssuesPerWeek,
        averagePRsPerWeek,
        totalIssues,
        totalPRs,
        openIssues,
        closedIssues,
        openPRs,
        closedPRs);
  }

  public RepositoryOverviewResponse getRepositoryOverview(RepositoryRequest data) {
    var repoOverview = githubClient.getRepositoryOverview(data.owner(), data.repository());
    return new RepositoryOverviewResponse(
        repoOverview.name(),
        repoOverview.description(),
        repoOverview.owner().login(),
        repoOverview.stars(),
        repoOverview.forks(),
        repoOverview.createdAt(),
        repoOverview.updatedAt(),
        repoOverview.openIssues(),
        repoOverview.language());
  }

  public RepositoryQualityResponse getRepositoryQuality(RepositoryRequest data) {
    Boolean hasReadme = false;
    Boolean hasLicense = false;
    Boolean hasGitIgnore = false;

    try {
      githubClient.getRepositoryFile(data.owner(), data.repository(), "README.md");
      hasReadme = true;
    } catch (Exception e) {
      hasReadme = false;
    }

    try {
      githubClient.getRepositoryFile(data.owner(), data.repository(), "LICENSE");
      hasLicense = true;
    } catch (Exception e) {
      hasLicense = false;
    }

    try {
      githubClient.getRepositoryFile(data.owner(), data.repository(), ".gitignore");
      hasGitIgnore = true;
    } catch (Exception e) {
      hasGitIgnore = false;
    }

    return new RepositoryQualityResponse(hasReadme, hasLicense, hasGitIgnore);
  }
}
