---
name: review
description: Comprehensive PR review against standards plus acceptance criteria validation
disable-model-invocation: true
uses:
  - rally-artifact-retrieval
---

# Workflow: Code Review (`review`)

**Goal:** Comprehensive PR review against standards + acceptance criteria validation

Comprehensive code review for PR readiness. Works for self-review before posting or peer review of others' code.

## 1. Identify and Retrieve Rally Artifact Details

Follow the **rally-artifact-retrieval** skill to identify and fetch the Rally artifact.

Use the artifact details (Name, Description, Acceptance Criteria) for validating acceptance criteria in Step 5.

If no artifact ID is found, skip acceptance criteria validation.

## 2. Gather Changes

Run `git diff master...HEAD` to get the complete diff for this branch.

If reviewing a specific commit, use `git show <commit-sha>` instead.

## 3. Identify Changed Files

List all modified files:

```bash
git diff master...HEAD --name-only
```

For each file with significant changes, read the full file to understand context (not just the diff hunks).

## 4. Review Checklist

Evaluate against the following criteria:

### Code Quality

- [ ] Follows project coding standards (Java 17+, Spring Boot conventions, Lombok usage)
- [ ] Proper error handling and edge cases covered
- [ ] No obvious bugs, logic errors, or race conditions
- [ ] Performance implications considered (N+1 queries, unnecessary API calls)
- [ ] Appropriate use of `@Value`, `@Builder`, and other Lombok annotations

### Architecture

- [ ] Follows established patterns in the codebase
- [ ] Appropriate separation of concerns (Service, Controller, Model layers)
- [ ] Not over-engineered; solves the problem directly
- [ ] Tool methods follow MCP conventions (`@Tool` annotation, proper descriptions)
- [ ] Reusable code placed appropriately (`service/`, `util/`, `model/`)

### Testing

- [ ] Tests added/updated for changed code
- [ ] Unit tests in `src/test/java/.../` mirror source structure
- [ ] Integration tests in `src/test/java/.../integration/` for API tests
- [ ] Mocks use Mockito appropriately
- [ ] Tests verify both success and error scenarios

### API Design

- [ ] Tool descriptions are clear and useful for LLM consumption
- [ ] Tool parameters have appropriate validation
- [ ] Error messages are informative and actionable
- [ ] Rally API responses are properly mapped to model classes

### Security

- [ ] No hardcoded credentials or tokens
- [ ] Input validation present where needed
- [ ] Auth tokens properly handled via AuthContext
- [ ] No sensitive data logged

### Logging

- [ ] Use `log.debug` for routine tool execution (not `log.info`) to avoid noisy logs
- [ ] Never log PII or sensitive data (user names, display names, project names, artifact names)
- [ ] Only log identifiers (formattedId, objectId) - not human-readable names
- [ ] Keep `log.info` for significant lifecycle events only (startup, shutdown, config changes)
- [ ] Use `log.warn` for recoverable issues, `log.error` for failures

## 5. Acceptance Criteria Validation

Using the Rally artifact details fetched in Step 1:

For each acceptance criterion:

- [ ] Criterion is addressed by the implementation
- [ ] Implementation matches the intent (not just the letter) of the AC
- [ ] Edge cases implied by AC are handled

**Output format:**

```
✅ AC 1: [criterion summary] — Implemented in [file/component]
⚠️ AC 2: [criterion summary] — Partially addressed, missing [detail]
❌ AC 3: [criterion summary] — Not implemented
```

## 6. Documentation Impact Assessment

### Code Documentation

- [ ] New/modified public methods have Javadoc comments
- [ ] Complex logic has explanatory comments
- [ ] Tool methods have clear `@Tool` description annotations

### Project Documentation

Evaluate if these need updates based on the changes:

| Document        | Needs Update?   | Reason                                   |
| --------------- | --------------- | ---------------------------------------- |
| README.md       | Yes/No          | [if yes, what changed]                   |
| DOCKER.md       | Yes/No          | [if yes, infrastructure changes]         |
| KAFKA_SETUP.md  | Yes/No          | [if yes, Kafka-related changes]          |

## 7. Test Coverage Verification

1. **Run tests and generate coverage report**:

   ```bash
   ./gradlew test jacocoTestReport
   ```

2. **Verify coverage for changed files**:

   - Parse `build/reports/jacoco/test/jacocoTestReport.xml` to extract instruction coverage for changed source files
   - All changed service classes must have ≥80% instruction coverage
   - Config/model classes are exempt

3. **Report results** in a table showing file, coverage percentage, and pass/fail status.

4. **Flag gaps**:
   - Source files with logic changes but no corresponding tests
   - Changed files below 80% coverage threshold
   - New service classes without unit tests

## 8. Output

### Summary

One paragraph: what this change does, why, and which Rally artifact it addresses.

### Acceptance Criteria Status

[Output from Step 5]

### Issues (by severity)

**🔴 Blockers** — Must fix before merge:

- Unmet acceptance criteria
- Failing tests
- Security vulnerabilities
- Critical bugs
  (list or "None")

**🟡 Concerns** — Should address, but not blocking:

- Missing test coverage
- Documentation gaps
- Performance concerns
  (list or "None")

**🟢 Nits** — Minor suggestions, take or leave:

- Code style preferences
- Optimization opportunities
  (list or "None")

### Documentation Recommendations

[Output from Step 6 - what docs need updating and why]

### Test Results

[Output from Step 7 - pass/fail status and coverage for changed files]

| File | Coverage | Status |
|------|----------|--------|
| [FileName.java] | [X.X%] | ✅/❌ |

**Coverage Threshold**: 80% minimum for changed files

### Questions

Anything unclear about the implementation that needs clarification.

---

**Verdict**: APPROVE / REQUEST CHANGES / NEEDS DISCUSSION

**PR Readiness**: Ready to post / Needs work on [specific items]
