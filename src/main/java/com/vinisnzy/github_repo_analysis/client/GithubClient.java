package com.vinisnzy.github_repo_analysis.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryCommitsResponse;
import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryFile;
import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryIssuesResponse;
import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryOverviewResponse;
import com.vinisnzy.github_repo_analysis.client.dto.response.GetRepositoryPRsResponse;

@FeignClient(name = "githubClient", url = "${github.api.base-url}")
public interface GithubClient {

        @GetMapping("/repos/{owner}/{repo}")
        GetRepositoryOverviewResponse getRepositoryOverview(@PathVariable String owner, @PathVariable String repo);

        @GetMapping("/repos/{owner}/{repo}/commits")
        List<GetRepositoryCommitsResponse> getRepositoryCommits(@PathVariable String owner,
                        @PathVariable String repo,
                        @RequestParam(value = "since", required = false) String since);

        @GetMapping("/repos/{owner}/{repo}/pulls")
        List<GetRepositoryPRsResponse> getRepositoryPRs(@PathVariable String owner, @PathVariable String repo,
                        @RequestParam(value = "since", required = false) String since,
                        @RequestHeader("Authorization") String GITHUB_TOKEN);

        @GetMapping("/repos/{owner}/{repo}/issues")
        List<GetRepositoryIssuesResponse> getRepositoryIssues(@PathVariable String owner, @PathVariable String repo,
                        @RequestParam(value = "since", required = false) String since,
                        @RequestHeader("Authorization") String GITHUB_TOKEN);

        @GetMapping("/repos/{owner}/{repo}/contents/{path}")
        GetRepositoryFile getRepositoryFile(@PathVariable String owner, @PathVariable String repo,
                        @PathVariable String path);
}
