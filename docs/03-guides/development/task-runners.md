# Task Runners for KMIP Adapter

This guide covers the three supported task runners for the KMIP Adapter project. Choose the one that best fits your workflow.

## Available Task Runners

### 1. Just (Recommended)
A modern command runner with a clean syntax and great developer experience.

**Installation:**
```bash
# macOS
brew install just

# Linux
curl --proto '=https' --tlsv1.2 -sSf https://just.systems/install.sh | bash -s -- --to ~/bin

# Windows
scoop install just
```

**Key Commands:**
```bash
# List all available commands
just --list

# Run tests
just test

# Run a specific test class
just test-class TEST=com.example.TestClass

# Run benchmarks
just benchmark

# Format code
just format
```

**Benefits:**
- Clean, readable syntax
- Built-in help system
- Cross-platform support
- No external dependencies (single binary)
- Parameter support
- Better error messages than Make

---

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

**Key Commands:**
```bash
# List all available commands
task --list

# Run tests
task test

# Run a specific test class
task test-class -- TEST=com.example.TestClass

# Run benchmarks
task benchmark

# Format code
task format
```

**Benefits:**
- YAML configuration
- Task dependencies
- Cross-platform support
- Built-in variables
- Clean output

---

### 3. Make
Traditional build automation tool, available on most Unix-like systems.

**Installation:**
- Pre-installed on most Unix-like systems
- Windows: Install via WSL, MinGW, or Chocolatey

**Key Commands:**
```bash
# Show help
make help

# Run tests
make test

# Run a specific test class
make test-class TEST=com.example.TestClass

# Run benchmarks
make benchmark

# Format code
make format
```

**Benefits:**
- Universally available
- No additional installation needed on Unix-like systems
- Well-known syntax
- Good for simple build tasks

## Which One Should I Use?

1. **For new developers**: Use **Just** - it has the best developer experience
2. **For complex workflows**: Use **Task** - better for complex dependencies
3. **For maximum compatibility**: Use **Make** - available everywhere

## Adding New Commands

1. **Just**: Add a new target to `justfile`
2. **Task**: Add a new task to `Taskfile.yml`
3. **Make**: Add a new target to `Makefile`

## Best Practices

- Keep commands consistent across all task runners
- Document new commands in this file
- Test commands on all supported platforms
- Prefer simple, single-purpose commands
- Include help text for all commands

## Troubleshooting

### Just
```bash
# Debug command execution
just --dry-run <command>

# Show command output in real-time
just --verbose <command>
```

### Task
```bash
# Show task details
task --summary <task>

# Debug task execution
task --debug <task>
```

### Make
```bash
# Show commands that would be executed
make -n <target>

# Debug makefile execution
make --debug <target>
```

## Performance
- **Just**: Fastest startup time
- **Task**: Slightly slower due to YAML parsing
- **Make**: Fast, but can be slower with complex Makefiles

Choose the tool that best fits your team's workflow and platform requirements. All three are configured to provide the same core functionality.
