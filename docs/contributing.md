# Contributing to KMIP Adapter

We welcome contributions from the community! Whether you're fixing a bug, adding a new feature, or improving the documentation, your help is appreciated.

## How to Contribute

1.  **Fork the Repository:**
    -   Start by forking the main repository to your own GitHub account.

2.  **Clone the Repository:**
    -   Clone your forked repository to your local machine:
        ```bash
        git clone https://github.com/your-username/kmip-adapter.git
        ```

3.  **Create a New Branch:**
    -   Create a new branch for your changes:
        ```bash
        git checkout -b feature/my-new-feature
        ```

4.  **Make Your Changes:**
    -   Make your changes to the codebase. Please follow the coding style and conventions used in the project.
    -   If you are adding a new feature, please also add corresponding tests.

5.  **Run the Tests:**
    -   Before submitting your changes, make sure that all the tests pass:
        ```bash
        ./gradlew test
        ```

6.  **Commit Your Changes:**
    -   Commit your changes with a clear and descriptive commit message. We follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification. This allows for easier tracking of changes and automated changelog generation.
    -   Your commit message should be structured as follows:
        ```
        <type>[optional scope]: <description>

        [optional body]

        [optional footer(s)]
        ```
    -   **Common types:**
        -   `feat`: A new feature
        -   `fix`: A bug fix
        -   `docs`: Documentation only changes
        -   `style`: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
        -   `refactor`: A code change that neither fixes a bug nor adds a feature
        -   `perf`: A code change that improves performance
        -   `test`: Adding missing tests or correcting existing tests
        -   `build`: Changes that affect the build system or external dependencies (example scopes: gradle, npm)
        -   `ci`: Changes to our CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs)
        -   `chore`: Other changes that don't modify `src` or `test` files
        -   `revert`: Reverts a previous commit
    -   **Example:**
        ```bash
        git commit -m "feat(parser): add support for MyNewStructure"
        ```

7.  **Push to Your Fork:**
    -   Push your changes to your forked repository:
        ```bash
        git push origin feature/my-new-feature
        ```

8.  **Create a Pull Request:**
    -   Open a pull request from your forked repository to the main repository.
    -   Provide a clear description of your changes and why they are needed.

## Coding Style

-   **Java:** Follow the Google Java Style Guide.
-   **Code Formatting:** Use the provided `.editorconfig` file to ensure consistent code formatting.
-   **Comments:** Write clear and concise comments where necessary. Use Javadoc for all public methods and classes.

## Bug Reports

If you find a bug, please open an issue on the GitHub repository. Please include the following information:

-   A clear and descriptive title.
-   A detailed description of the bug, including steps to reproduce it.
-   The expected behavior and the actual behavior.
-   The version of the library you are using.

## Feature Requests

If you have an idea for a new feature, please open an issue on the GitHub repository. Please include the following information:

-   A clear and descriptive title.
-   A detailed description of the proposed feature and why it would be useful.

Thank you for contributing to the KMIP Adapter!
