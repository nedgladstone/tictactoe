---
name: understand-rally-story
description: Fetches and explains a Rally user story to ensure shared understanding. Shows the current user and workspace, presents the story details, and allows the user to confirm or correct the story selection. Use when the user wants to understand, read, or review a Rally user story before working on it.
---

# Understand Rally Story

This skill fetches and explains a Rally user story to ensure shared understanding before implementation begins.

## Required Information

Before starting, you need:
- **Story Formatted ID** (e.g., `US123`): The Rally formatted ID of the story to understand. If not provided, ask the user for it or use the `rally-artifact-retrieval` skill to find it.

## Steps

1. Get the current user and workspace information:
   - Use `getRallyCurrentUser` to get the current user's display name
   - Use `getRallyWorkspace` to get the current workspace name
   - Present this to the user:
     ```
     ## Rally Context
     
     **Username**: [display name]
     **Workspace**: [workspace name]
     ```

2. Get the user story details:
   - Use `getRallyArtifactDetails` with `formattedId` and `includeChildren: true`
   - Extract: Name, Description, Acceptance Criteria, Notes, current state

3. Present a summary to the user:
   ```
   ## Story: [FormattedID] - [Name]
   
   **Current State**: [state]
   **Owner**: [owner or Unassigned]
   
   ### Description
   [description summary]
   
   ### Acceptance Criteria
   [bullet list of acceptance criteria]

   ### Notes
   [notes summary]
   
   ### My Understanding
   [Your interpretation of what needs to be done]
   ```

4. Confirm the correct story:
   - Ask the user: "Is this the correct story you want to work on?"
   - If the user says no or indicates it's the wrong story:
     - Ask them to provide the correct formatted ID
     - Go back to step 2 with the new formatted ID
   - If the user confirms it's correct, proceed to the next step

5. **CHECKPOINT**: Ask the user to confirm understanding and if they want any changes:
   ```
   Does my understanding of this story look correct?
   
   Would you like to make any changes to the story's description or notes before we proceed?
   ```

6. Make any changes to the story that the user requested:
   - Use `updateRallyArtifactTextField` to update the description or notes if requested
   - Pause for 1 second before using any other Rally tools to avoid a concurrency error

## Output

After completing this skill, you will have:
- Confirmed the story formatted ID
- A shared understanding of the story requirements
- Any requested updates applied to the story
