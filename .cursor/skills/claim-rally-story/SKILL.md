---
name: claim-rally-story
description: Assigns a Rally user story to the current user, moves it to In-Progress, and optionally creates a GitHub PR linked to the story. Use when the user wants to start working on, claim, or take ownership of a Rally user story.
---

# Claim Rally Story

This skill assigns a Rally user story to the current user and prepares it for implementation.

## Required Information

Before starting, you need:
- **Story Formatted ID** (e.g., `US123`): The Rally formatted ID of the story to claim. If not provided, ask the user for it.
- **Story Name**: The name/title of the story (used for commit messages and PR title). If not known, fetch it using `getRallyArtifactDetails`.
- **Story Description**: The description of the story (used for PR body). If not known, fetch it using `getRallyArtifactDetails`.

## Steps

1. Get current user info:
   - Use `getRallyCurrentUser` to get the user's display name and reference

2. Assign the story to the current user:
   - Use `assignRallyArtifact` with the story's `formattedId` and user's display name
   - Pause for 1 second before using any other Rally tools to avoid a concurrency error

3. Move the story to In-Progress:
   - Use `moveRallyArtifact` with `state: "In-Progress"`

4. Create a branch in git and a PR in GitHub:
   - Ask the user if it is ok to create a GitHub PR for this story. If they say no, skip this step and proceed.
   - Create a local git branch whose name is the formatted ID of this story and switch to that branch.
   - Create an empty commit in git using the --allow-empty flag on git commit. The commit message should contain the formatted ID of this story plus the Name of this story.
   - Push that commit to GitHub.
   - Create a pull request in GitHub, specifying: --title (the same text as the commit message), --body (the description of the story, formatted with GitHub Flavored Markdown, GFM), --head (the branch name), and --base (master) so that gh doesn't need to prompt for any additional information.
     - Convert HTML from Rally description to GFM (e.g., `<ul><li>` → `- `, `<strong>` → `**`, `<p>` → newlines)
     - Use a HEREDOC to preserve formatting in the body:
       ```bash
       gh pr create --title "US123: Story name" --body "$(cat <<'EOF'
       Story description here...
       
       **Acceptance criteria:**
       - First criterion
       - Second criterion
       EOF
       )" --head US123 --base master
       ```
   - Use `linkPullRequestToArtifact` to link this PR into the Rally story.
   - Show the user the details of the pull request.

5. Confirm to the user:
   ```
   Story [FormattedID] is now:
   - Assigned to: [your name]
   - State: In-Progress
   ```

## Output

After completing this skill, you will have:
- The story assigned to the current user
- The story in "In-Progress" state
- (Optionally) A git branch and GitHub PR linked to the story
