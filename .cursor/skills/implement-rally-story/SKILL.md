---
name: implement-rally-story
description: Guides through the complete workflow of implementing a Rally user story, including reading the story, assigning it, breaking it into tasks, implementing each task with code and tests, and marking the story complete. Use when the user wants to work on, implement, or develop a Rally user story.
---

# Implement Rally User Story

This skill provides a structured workflow for implementing a Rally user story from start to finish, with checkpoints for user review at each stage.

## Prerequisites

- Rally MCP server must be connected
- Use the `rally-artifact-retrieval` skill to identify the story ID if not provided

## Workflow Overview

```
Phase 1: Understand the Story
Phase 2: Claim the Story  
Phase 3: Break Down into Tasks
Phase 4: Implement Each Task (iterative)
Phase 5: Mark Story Complete
```

---

## Phase 1: Understand the Story

Use the `understand-rally-story` skill.

**Input needed**: Story Formatted ID (e.g., `US123`)

**Output**: Confirmed story ID, shared understanding, any requested updates applied.

---

## Phase 2: Claim the Story

Use the `claim-rally-story` skill.

**Input needed**: 
- Story Formatted ID (from Phase 1)
- Story Name (from Phase 1)
- Story Description (from Phase 1)

**Output**: Story assigned to current user, moved to In-Progress, optionally a GitHub PR created.

---

## Phase 3: Break Down into Tasks

Use the `create-rally-tasks` skill.

**Input needed**:
- Story Formatted ID (from Phase 1)
- Story Details (from Phase 1)

**Output**: List of task formatted IDs (e.g., `TA456`, `TA457`) created under the story.

---

## Phase 4: Implement Each Task

For each task created in Phase 3, use the `implement-rally-task` skill.

**Input needed for each task**:
- Task Formatted ID (from Phase 3)
- Parent Story Formatted ID (from Phase 1)
- Task Name (from Phase 3)
- Task Description (from Phase 3)

**Output**: Each task marked as Completed, code changes committed.

Repeat until all tasks are complete.

---

## Phase 5: Mark Story Complete

Use the `complete-rally-story` skill.

**Input needed**:
- Story Formatted ID (from Phase 1)
- Story Name (from Phase 1)

**Output**: Story marked as Ready, any final updates applied.

---

## Quick Reference: Rally MCP Tools

| Action | Tool | Key Parameters |
|--------|------|----------------|
| Get story details | `getRallyArtifactDetails` | `formattedId`, `includeChildren` |
| Get current user | `getRallyCurrentUser` | (none) |
| Get workspace | `getRallyWorkspace` | (none) |
| Assign story | `assignRallyArtifact` | `formattedId`, `assigneeDisplayName` |
| Assign task | `assignRallyTask` | `formattedId`, `assigneeDisplayName` |
| Move state | `moveRallyArtifact` | `formattedId`, `state` |
| Create task | `createRallyTask` | `formattedId`, `name`, `description` |
| Get tasks | `getRallyArtifactTasks` | `formattedId` |
| Mark ready | `updateRallyArtifactReadyOrBlocked` | `formattedId`, `ready` |
| Update text field | `updateRallyArtifactTextField` | `formattedId`, `fieldName`, `value` |
| Post discussion | `postRallyDiscussion` | `formattedId`, `text` |
| Link PR | `linkPullRequestToArtifact` | `formattedId`, `pullRequestUrl` |

## State Values

- **User Stories**: `Defined`, `In-Progress`, `Completed`, `Accepted`
- **Tasks**: `Defined`, `In-Progress`, `Completed`
