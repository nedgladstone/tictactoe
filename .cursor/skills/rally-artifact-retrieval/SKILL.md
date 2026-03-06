---
name: rally-artifact-retrieval
description: This document defines the standard strategy for identifying and fetching Rally artifacts.
disable-model-invocation: false
---

# Rally Artifact Retrieval

## Rally Artifact ID Pattern

**Pattern**: `(US|S|DE|F|TA|TC|TS)\d+`

**Description**: Matches Rally FormattedIDs for:

- `US` or `S` - User Stories
- `DE` - Defects
- `F` - Features
- `TA` - Tasks
- `TC` - Test Cases
- `TS` - Test Sets

**Examples**:

- `US12345` or `S12345`
- `DE6789`
- `F54321`

## Standard Retrieval Strategy

Use this priority order to find the Rally artifact ID:

1. **User's prompt/input**: Check if the user explicitly provided a FormattedID

2. **Branch name**: Parse current branch for pattern `{TYPE}{ID}-description`

   ```bash
   git branch --show-current
   ```

   Extract ID matching the Rally Artifact ID Pattern above (e.g., `S12345` from `S12345-add-feature`)

3. **Commit messages**: Search recent commits for artifact IDs

   ```bash
   git log master..HEAD --oneline
   ```

   Look for FormattedIDs in commit messages

4. **Ask user**: If not found in any of the above, ask the user for the FormattedID and **STOP**

## Fetching Artifact Details

Once identified, fetch the artifact details from Rally to retrieve:

- Name
- Description
- Acceptance Criteria
- Current state

**Note**: Workflows may skip steps based on their context. For example, a workflow that expects the user to provide the ID may skip steps 2-3.
