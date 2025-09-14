# Build System and Task Runners

This guide covers the three supported build systems and task runners for the KMIP Adapter project. All three provide equivalent functionality, so you can choose the one that best fits your workflow.

## Available Options

### 1. Just (Recommended)
A modern command runner with clean syntax and great developer experience.

**Installation:**
```bash
# macOS
brew install just

# Linux
curl --proto '=https' --tlsv1.2 -sSf https://just.systems/install.sh | bash -s -- --to ~/bin

# Windows
scoop install just
```

### 2. Task
A simple task runner written in Go, good for complex workflows.

**Installation:**
```bash
# macOS
brew install go-task/tap/go-task

# Linux
sh -c "$(curl --location https://taskfile.dev/install.sh)" -- -d -b ~/.local/bin

# Windows
scoop install task
```

### 3. Make
Traditional build automation tool, available on most Unix-like systems.

**Installation:**
- Pre-installed on most Unix-like systems
- Windows: Install via WSL, MinGW, or Chocolatey

## Common Tasks

### Development Workflow

| Task | Just | Task | Make |
|------|------|------|------|
| List commands | `just --list` | `task --list` | `make help` |
| Install deps | `just install` | `task install` | `make install` |
| Run all tests | `just test` | `task test` | `make test` |
| Run specific test | `just test-class TEST=com.example.Test` | `task test-class -- TEST=com.example.Test` | `make test-class TEST=com.example.Test` |
| Run with integration tests | `just test-integration` | `task test-integration` | `make test-integration` |
| Format code | `just format` | `task format` | `make format` |
| Run benchmarks | `just benchmark` | `task benchmark` | `make benchmark` |

### CI/CD Pipeline

| Task | Just | Task | Make |
|------|------|------|------|
| Full CI pipeline | `just ci-all` | `task ci-all` | `make ci-all` |
| Fast CI (no perf) | `just ci-all-fast` | `task ci-all-fast` | `make ci-all-fast` |
| Generate reports | `just reports` | `task reports` | `make reports` |
| Clean artifacts | `just clean` | `task clean` | `make clean` |

## Integration Tests

The build system handles integration tests gracefully:

- Integration tests are identified by the `*IT.java` naming convention
- The `with-integration` Maven profile must be active to run integration tests
- When no integration tests are found, the build continues without failing
- Test reports will show a commented-out section when no integration tests are present

## Artifacts and Reports

Build outputs are organized in the `target/ci-artifacts` directory:

```
target/ci-artifacts/
├── unit/              # Unit test reports (JUnit XML)
├── integration/       # Integration test reports (JUnit XML)
├── coverage/          # Code coverage reports (JaCoCo)
├── performance/       # Performance test results
├── logs/             # Build and test logs
└── summary.md        # Consolidated test summary
```

## Customization

### Environment Variables

- `MAVEN_OPTS`: Pass JVM options to Maven
- `TEST_OPTS`: Pass additional test options

### Maven Profiles

The build system uses these Maven profiles:

- `with-integration`: Include integration tests
- `perf`: Run performance benchmarks
- `perf-fast`: Run lighter performance tests (fewer iterations)
- `coverage-strict`: Enable strict coverage checks

## Best Practices

1. **Local Development**: Use the fast CI pipeline for quick feedback
   ```bash
   just ci-all-fast
   # or
   task ci-all-fast
   # or
   make ci-all-fast
   ```

2. **CI/CD**: Use the full pipeline for complete validation
   ```bash
   just ci-all
   # or
   task ci-all
   # or
   make ci-all
   ```

3. **Troubleshooting**: Check `target/ci-artifacts/logs/` for detailed logs

4. **Customization**: Extend the build files by adding new targets/tasks as needed

## Choosing a Task Runner

### Just (Recommended)
- **Best for**: Most users, especially those who value clean syntax
- **Benefits**:
  - Clean, readable syntax
  - Built-in help system
  - Cross-platform support
  - No external dependencies (single binary)
  - Better error messages than Make

### Task
- **Best for**: Complex workflows and YAML fans
- **Benefits**:
  - YAML configuration
  - Task dependencies
  - Cross-platform support
  - Built-in variables
  - Clean output

### Make
- **Best for**: Traditional Unix environments
- **Benefits**:
  - Universally available
  - No additional installation needed
  - Well-known syntax
