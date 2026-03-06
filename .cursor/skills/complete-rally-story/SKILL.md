---
name: complete-rally-story
description: Finalizes a Rally user story after all tasks are complete. Verifies task completion, presents a summary, allows final edits, and marks the story as Ready. Use when the user wants to finish, complete, or close out a Rally user story.
---

# Complete Rally Story

This skill finalizes a Rally user story once all implementation tasks are done.

## Required Information

Before starting, you need:
- **Story Formatted ID** (e.g., `US123`): The Rally formatted ID of the story to complete. If not provided, ask the user for it.
- **Story Name**: The name of the story (for display purposes). If not known, fetch using `getRallyArtifactDetails`.

## Steps

1. Verify all tasks are complete:
   - Use `getRallyArtifactTasks` with the story's `formattedId` to confirm all tasks show "Completed"
   - If any tasks are not completed, inform the user and ask how to proceed

2. Present summary:
   ```
   ## Story Implementation Complete
   
   **Story**: [FormattedID] - [Name]
   
   ### Completed Tasks
   - TA456: [name] ✓
   - TA457: [name] ✓
   ...
   
   ### Summary of Changes
   [High-level summary of what was implemented]
   ```

3. Review the implementation:
   - Ask the user: "Would you like me to review the implementation before marking the story complete?"
   - If the user says yes:
     - Use the `review` skill to perform a comprehensive code review
     - The review skill will validate against coding standards and acceptance criteria
     - Address any blockers or concerns identified by the review before proceeding
   - If the user says no, proceed to the next step

4. **CHECKPOINT**: Ask the user to confirm story completion:
   ```
   Do you agree that this story is complete?
   
   Before marking it ready, would you like to:
   - Make any changes to the story's description or notes?
   - Post a discussion entry on this story?
   ```
   - If the user wants to update the description or notes:
     - Use `updateRallyArtifactTextField` to make the changes
     - Pause for 1 second before using any other Rally tools to avoid a concurrency error
   - If the user wants to post a discussion entry:
     - Ask for the content of the discussion post
     - Use `postRallyDiscussion` to add the discussion entry
     - Pause for 1 second before using any other Rally tools to avoid a concurrency error
   - If the user does not agree the story is complete:
     - Get feedback on what needs to change
     - Direct them to use the `implement-rally-task` skill as appropriate

5. Mark story as Ready:
   - Use `updateRallyArtifactReadyOrBlocked` with `ready: true`
   - Pause for 1 second before using any other Rally tools to avoid a concurrency error

6. Confirm completion:
   ```
   Story [FormattedID] has been marked as Ready.
   
   All implementation work is complete. The story is ready for review/acceptance.
   ```

## Output

After completing this skill, you will have:
- The story marked as "Ready" in Rally
- Any requested final updates applied to the story
- (Optionally) A discussion entry posted on the story
