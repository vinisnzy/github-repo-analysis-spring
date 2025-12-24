package com.vinisnzy.github_repo_analysis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinisnzy.github_repo_analysis.dto.request.RepositoryRequest;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryCommitsResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryIssuesPRsResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryOverviewResponse;
import com.vinisnzy.github_repo_analysis.dto.response.RepositoryQualityResponse;
import com.vinisnzy.github_repo_analysis.service.RepositoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/repositories")
@RequiredArgsConstructor
public class RepositoryController {
  private final RepositoryService repositoryService;

  @GetMapping("/commits")
  public ResponseEntity<RepositoryCommitsResponse> getRepositoryCommits(@RequestBody RepositoryRequest data) {
    return ResponseEntity.ok(repositoryService.getRepositoryCommits(data));
  }

  @GetMapping("/issues-prs")
  public ResponseEntity<RepositoryIssuesPRsResponse> getRepositoryIssuesPRs(@RequestBody RepositoryRequest data) {
    return ResponseEntity.ok(repositoryService.getRepositoryIssuesPRs(data));
  }

  @GetMapping("/overview")
  public ResponseEntity<RepositoryOverviewResponse> getRepositoryOverview(@RequestBody RepositoryRequest data) {
    return ResponseEntity.ok(repositoryService.getRepositoryOverview(data));
  }

  @GetMapping("/quality")
  public ResponseEntity<RepositoryQualityResponse> getRepositoryQuality(@RequestBody RepositoryRequest data) {
    return ResponseEntity.ok(repositoryService.getRepositoryQuality(data));
  }
}