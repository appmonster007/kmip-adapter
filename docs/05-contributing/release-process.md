# Release Process

This document outlines the steps to create a new release of the KMIP Adapter.

## Prerequisites

- JDK 21+ installed
- Maven 3.6+ installed
- GPG setup for signing artifacts
- Push access to the repository
- Permissions to publish to the Maven Central repository

## Preparation

1. **Update Dependencies**
   ```bash
   mvn versions:display-dependency-updates
   mvn versions:display-plugin-updates
   ```
   Update any outdated dependencies if needed.

2. **Update Version**
   - Update version in `pom.xml` (remove -SNAPSHOT)
   - Update version in `README.md`
   - Update version in any other relevant files

3. **Update Changelog**
   - Update `CHANGELOG.md` with all changes since the last release
   - Group changes by type (Added, Changed, Fixed, Removed, etc.)
   - Include links to relevant issues/PRs

4. **Commit Changes**
   ```bash
   git add .
   git commit -m "Prepare for release X.Y.Z"
   git tag -a vX.Y.Z -m "Version X.Y.Z"
   git push origin vX.Y.Z
   ```

## Building the Release

1. **Clean and Verify**
   ```bash
   mvn clean verify
   ```

2. **Run All Tests**
   ```bash
   mvn test
   ```

3. **Run Integration Tests**
   ```bash
   mvn -Pwith-integration test
   ```

4. **Create Source and Javadoc JARs**
   ```bash
   mvn source:jar javadoc:jar
   ```

## Releasing to Maven Central

1. **Stage the Release**
   ```bash
   mvn clean deploy -P release -Dgpg.passphrase="your-passphrase"
   ```

2. **Close the Staging Repository**
   - Go to [OSSRH](https://s01.oss.sonatype.org/)
   - Log in with your Sonatype credentials
   - Go to "Staging Repositories"
   - Find and select the repository for your release
   - Click "Close" and confirm

3. **Release the Artifacts**
   - After closing, select the repository
   - Click "Release" and confirm

## GitHub Release

1. **Create a New Release**
   - Go to the [Releases](https://github.com/purplebean/kmip-adapter/releases) page
   - Click "Draft a new release"
   - Tag version: `vX.Y.Z`
   - Release title: `KMIP Adapter X.Y.Z`
   - Description: Copy from CHANGELOG.md
   - Attach the following files:
     - `target/kmip-adapter-X.Y.Z.jar`
     - `target/kmip-adapter-X.Y.Z-sources.jar`
     - `target/kmip-adapter-X.Y.Z-javadoc.jar`
   - Click "Publish release"

## Post-Release

1. **Update to Next Development Version**
   - Update version in `pom.xml` to `X.Y.(Z+1)-SNAPSHOT`
   - Commit the change:
     ```bash
     git add .
     git commit -m "Prepare for next development iteration"
     git push origin main
     ```

2. **Announce the Release**
   - Post on relevant forums/mailing lists
   - Update project website if applicable
   - Notify major users/dependents

## Rollback Plan

If a release needs to be rolled back:

1. **Before Maven Central Release**
   - Drop the staging repository in OSSRH
   - Delete the git tag: `git tag -d vX.Y.Z`
   - Reset the main branch to the previous commit

2. **After Maven Central Release**
   - Create a new patch release that reverts the changes
   - Clearly document the rollback in the changelog

## Release Cadence

- **Major (X.0.0)**: Breaking changes, new major features
- **Minor (X.Y.0)**: New features, backward-compatible
- **Patch (X.Y.Z)**: Bug fixes, security updates

Aim for a regular release schedule (e.g., every 3 months for minor releases, patch releases as needed).

## Release Checklist

- [ ] All tests pass
- [ ] Documentation is up to date
- [ ] Changelog is updated
- [ ] Version numbers are updated
- [ ] Dependencies are up to date
- [ ] Release notes are prepared
- [ ] Artifacts are signed
- [ ] Release is published to Maven Central
- [ ] GitHub release is created
- [ ] Version is bumped for next development iteration
- [ ] Release is announced
