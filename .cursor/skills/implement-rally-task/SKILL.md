---
name: implement-rally-task
description: Implements a single Rally task including planning, coding, testing, review, and marking complete. Works through the full implementation cycle with user checkpoints. Use when the user wants to implement, work on, or complete a specific Rally task.
---

# Implement Rally Task

This skill guides the implementation of a single Rally task from start to completion.

## Required Information

Before starting, you need:
- **Task Formatted ID** (e.g., `TA123`): The Rally formatted ID of the task to implement. If not provided, ask the user for it.
- **Parent Story Formatted ID** (e.g., `US123`): The Rally formatted ID of the parent story. Useful for context. If not known, it can be retrieved from the task details.
- **Task Name**: The name of the task (used for commit messages). If not known, fetch using `getRallyArtifactDetails`.
- **Task Description**: What the task involves. If not known, fetch using `getRallyArtifactDetails`.

## Steps

### 1. Start the Task

1. Move task to In-Progress:
   - Use `moveRallyArtifact` with `formattedId` (the task ID) and `state: "In-Progress"`
   - Pause for 1 second before using any other Rally tools to avoid a concurrency error

2. Assign task to current user:
   - Use `assignRallyTask` with the task's `formattedId`

3. Get task details if needed:
   - Use `getRallyArtifactDetails` with the task's `formattedId`

4. Explain the task:
   ```
   ## Working on: [Task FormattedID] - [Task Name]
   
   **Description**: [task description]
   
   **What this involves**:
   [Your explanation of the implementation work]
   ```

### 2. Plan the Implementation

1. Analyze the codebase to understand:
   - Where changes need to be made
   - What patterns to follow
   - What dependencies exist

2. Present the implementation plan:
   ```
   ## Implementation Plan for [Task ID]
   
   ### Files to Modify
   - `path/to/file1.java`: [what changes]
   - `path/to/file2.java`: [what changes]
   
   ### New Files (if any)
   - `path/to/newfile.java`: [purpose]
   
   ### Approach
   1. [Step 1 description]
   2. [Step 2 description]
   ...
   
   ### Tests
   - [Test approach description]
   ```

3. **CHECKPOINT**: Ask for user feedback on the plan.
   - User may suggest different approaches
   - User may identify missing considerations
   - Incorporate feedback before proceeding

### 3. Implement the Code

1. Make the code changes according to the approved plan

2. Implement tests for the changes

3. Present a summary of changes:
   ```
   ## Changes Made
   
   ### Modified Files
   - `file1.java`: [summary of changes]
   
   ### New Files
   - `newfile.java`: [purpose]
   
   ### Tests Added
   - `TestFile.java`: [what's tested]
   ```

### 4. Do NOT Run ANY Tests

1. Output a message that at this point, the LLM would run tests, but that is being skipped since this is a demo.

### 5. Task Completion Check

1. **CHECKPOINT**: Ask the user if they consider the task complete:
   ```
   Task [TA...] implementation is complete.
   
   Do you consider this task complete? (If not, let me know what needs adjustment.)
   ```

2. If user says no:
   - Get feedback on what needs to change
   - Loop back to appropriate step (2, 3, or 4)

3. If user says yes:
   - Review and commit changes:
     - Run `git status` and `git diff` to show the user all changes
     - Present the changes and ask:
       ```
       Here are the changes for this task. Would you like to make any modifications before committing?
       
       If everything looks good, I'll commit these changes.
       ```
     - If the user wants modifications:
       - Make the requested changes
       - Show `git status` and `git diff` again
       - Repeat until user approves
     - If the user approves the commit:
       - Commit the changes with message: `[Task FormattedID]: [Task Name]`
       - Example: `TA123: Implement user validation`
       - Use a HEREDOC for the commit message:
         ```bash
         git add -A && git commit -m "$(cat <<'EOF'
         TA123: Task name here
         EOF
         )"
         ```
     - Confirm the commit:
       ```
       Changes committed: [commit hash]
       ```
   - Mark task as Completed:
     - Use `moveRallyArtifact` with `state: "Completed"`
   - Pause for 1 second before using any other Rally tools to avoid a concurrency error
   - Confirm:
     ```
     Task [TA...] marked as Completed.
     ```

## Output

After completing this skill, you will have:
- The task marked as "Completed" in Rally
- Code changes committed to git with a message referencing the task ID and name
