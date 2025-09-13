# Pull Request Process

This guide explains how to contribute code changes to the KMIP Adapter project through pull requests (PRs).

## Before You Start

1. **Check for Existing Issues**
   - Search the [issue tracker](https://github.com/your-org/kmip-adapter/issues) to see if the issue is already reported
   - If it's a new issue, consider creating one before starting work

2. **Announce Your Intentions**
   - Comment on the issue that you plan to work on it
   - This helps avoid duplicate work

## Setting Up

1. **Fork the Repository**
   ```bash
   git clone https://github.com/your-username/kmip-adapter.git
   cd kmip-adapter
   git remote add upstream https://github.com/your-org/kmip-adapter.git
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## Making Changes

1. **Follow the Code Style**
   - Adhere to the [code style guide](./code-style.md)
   - Run the formatter: `mvn formatter:format`

2. **Write Tests**
   - Add unit tests for new functionality
   - Update existing tests if needed
   - Ensure all tests pass: `mvn test`

3. **Update Documentation**
   - Update relevant documentation
   - Add Javadoc for new public APIs
   - Update examples if they're affected

4. **Commit Your Changes**
   - Write clear, concise commit messages
   - Reference issues in commit messages: `Fix #123: Description of fix`
   ```bash
   git add .
   git commit -m "Fix #123: Description of fix"
   ```

5. **Keep Your Branch Updated**
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

## Submitting a Pull Request

1. **Push Your Changes**
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create the PR**
   - Go to your fork on GitHub
   - Click "New Pull Request"
   - Select the base branch (usually `main`)
   - Select your feature branch
   - Click "Create Pull Request"

3. **PR Title and Description**
   - **Title**: Clear, concise description of the change
     - Format: `[Category] Brief description`
     - Example: `[Security] Fix XSS vulnerability in login form`
   - **Description**:
     - Reference the issue: `Fixes #123`
     - Describe the problem and solution
     - Include any relevant screenshots or test results
     - Check all that apply in the PR template

4. **Review Process**
   - A maintainer will review your PR
   - Address all review comments
   - Update the PR as needed
   - All CI checks must pass

## Code Review Guidelines

### As a PR Author
- Be responsive to review comments
- Keep commits focused and logical
- Squash fixup commits before merging
- Update documentation as needed

### As a Reviewer
- Be constructive and respectful
- Focus on the code, not the author
- Explain the "why" behind requested changes
- Use GitHub's suggestion feature when possible

## After Approval

1. **Squash Commits** (if needed)
   ```bash
   git rebase -i HEAD~n  # where n is the number of commits to squash
   git push --force-with-lease
   ```

2. **Merge the PR**
   - A maintainer will merge the PR
   - The PR will be squashed and merged with a single commit message

3. **Clean Up**
   - Delete the feature branch
   - Update your local repository
   ```bash
   git checkout main
   git pull upstream main
   git branch -d feature/your-feature-name
   git push origin --delete feature/your-feature-name
   ```

## Common Issues

### Merge Conflicts
```bash
git fetch upstream
git rebase upstream/main
# Resolve conflicts
git add .
git rebase --continue
```

### Failing Tests
- Run tests locally before pushing
- Check the CI logs for details
- Fix all test failures before requesting review

### Stale Branch
```bash
git checkout your-branch
git fetch upstream
git rebase upstream/main
# Resolve conflicts if any
git push --force-with-lease
```

## Best Practices

1. **Small, Focused PRs**
   - Keep PRs small and focused on a single feature/bug
   - Split large changes into multiple PRs when possible

2. **Descriptive Commits**
   - Use the imperative mood ("Add feature" not "Added feature")
   - Keep the first line under 50 characters
   - Include a blank line between the subject and body
   - Reference issues in the body

3. **Testing**
   - Add tests for new functionality
   - Update tests for bug fixes
   - Ensure all tests pass before submitting

4. **Documentation**
   - Update relevant documentation
   - Add examples for new features
   - Document any breaking changes

5. **Performance**
   - Consider performance implications
   - Add benchmarks if performance is critical
   - Document any performance trade-offs

## Template

```markdown
## Description

Fixes #issue_number

### Changes
- List of changes made

### Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing performed

### Documentation
- [ ] Javadoc added/updated
- [ ] User guide updated
- [ ] API documentation updated

### Related PRs
- Related PR #1
- Related PR #2
```
