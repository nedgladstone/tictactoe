---
name: create-rally-tasks
description: Creates Rally tasks under a user story based on analysis of the requirements. Reviews existing tasks, proposes new ones, and creates approved tasks. Use when the user wants to break down a Rally story into tasks or add tasks to a story.
---

# Create Rally Tasks

This skill breaks down a Rally user story into implementation tasks.

## Required Information

Before starting, you need:
- **Story Formatted ID** (e.g., `US123`): The Rally formatted ID of the parent story. If not provided, ask the user for it.
- **Story Details**: Understanding of what the story requires (description, acceptance criteria). If not known, fetch using `getRallyArtifactDetails` with `includeChildren: true`.

## Steps

1. Check for existing tasks:
   - Use `getRallyArtifactTasks` with the story's `formattedId` to see if tasks already exist

2. If tasks exist, present them:
   ```
   ## Existing Tasks for [FormattedID]
   
   | ID | Name | State | Owner |
   |----|------|-------|-------|
   | TA123 | Task name | Defined | Unassigned |
   ```

3. If no tasks exist, or they don't seem sufficient, or user wants new ones, propose tasks:
   ```
   ## Proposed Tasks
   
   Based on the story requirements, I suggest that we add these tasks:
   
   1. **[Task Name]**: [Brief description]
   2. **[Task Name]**: [Brief description]
   ...
   ```

4. **CHECKPOINT**: Ask the user to review and modify the task list.
   - User may add, remove, or rename tasks
   - User may provide additional details

5. Create approved tasks:
   - Use `createRallyTask` for each approved task
   - Include meaningful descriptions
   - Pause for 1 second after each task creation to avoid a concurrency failure

6. Confirm task creation:
   ```
   Created [N] tasks under [FormattedID]:
   - TA456: [name]
   - TA457: [name]
   ...
   ```

## Output

After completing this skill, you will have:
- A list of task formatted IDs (e.g., `TA456`, `TA457`) created under the story
- Each task in "Defined" state, ready to be worked on
