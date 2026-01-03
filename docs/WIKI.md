# 📖 Java REST Assured - Project Wiki

Welcome to the comprehensive wiki for the Java REST Assured project. This wiki covers security policies, code quality standards, contribution guidelines, and operational procedures.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Security Framework](#security-framework)
3. [Code Quality Standards](#code-quality-standards)
4. [Getting Started](#getting-started)
5. [Contribution Guidelines](#contribution-guidelines)
6. [CI/CD Pipelines](#cicd-pipelines)
7. [Troubleshooting](#troubleshooting)
8. [Resources](#resources)

---

## Project Overview

### What is Java REST Assured?

Java REST Assured is a comprehensive testing framework for REST APIs with:
- **Framework**: REST Assured 5.4.0 for API testing
- **Test Framework**: TestNG 7.9.0 with parallel execution
- **Reporting**: Allure 2.25.0 with GitHub Pages deployment
- **Java Version**: Java 21 LTS with Maven 3.6+
- **CI/CD**: GitHub Actions with automated PR workflows

### Key Features

✅ **API Testing** - Comprehensive REST API testing with REST Assured  
✅ **Test Reporting** - Beautiful Allure reports deployed to GitHub Pages  
✅ **Code Quality** - SonarQube integration with quality gates  
✅ **Security** - GitHub security features + SAST scanning  
✅ **Automation** - GitHub Actions for CI/CD and auto-PR workflows  
✅ **Best Practices** - POJO patterns, Page Object Model, soft assertions  

### Repository Structure

```
java-rest-assured/
├── src/
│   ├── main/java/          # Main application code
│   └── test/
│       ├── java/           # Test cases (48+ tests)
│       └── resources/      # Test data and configurations
├── .github/
│   ├── workflows/          # GitHub Actions workflows
│   └── CODEOWNERS          # Code ownership rules
├── docs/
│   ├── SECURITY_CONFIGURATION.md  # Security setup guide
│   └── WIKI.md            # This file
├── pom.xml                # Maven configuration
├── SECURITY.md            # Vulnerability reporting policy
└── README.md              # Project documentation
```

---

## Security Framework

### Overview

The project implements a comprehensive security framework covering:
- Vulnerability reporting procedures
- Code ownership and review requirements
- GitHub security features (Secret Scanning, Dependabot, Code Scanning)
- Patch management timelines
- Security best practices

### Key Documents

| Document | Purpose | Location |
|----------|---------|----------|
| **SECURITY.md** | Vulnerability reporting policy | `/SECURITY.md` |
| **CODEOWNERS** | Code ownership and review requirements | `/.github/CODEOWNERS` |
| **Security Configuration** | Setup guides for security features | `/docs/SECURITY_CONFIGURATION.md` |

### Vulnerability Reporting

**Never** create public GitHub issues for security vulnerabilities. Instead:

1. Use GitHub's **Private Advisory System**
2. Navigate to **Security → Report a vulnerability**
3. Fill in vulnerability details
4. Await our response (24 hours)

### Response Timeline

| Severity | Response Time | Patch Timeline |
|----------|---------------|----------------|
| 🔴 Critical (CVSS 9-10) | 24-48 hours | ASAP, within 48 hours |
| 🟠 High (CVSS 7-8.9) | 24 hours | 1-2 weeks |
| 🟡 Medium (CVSS 4-6.9) | 1 week | Quarterly release |
| 🟢 Low (CVSS 0-3.9) | Best effort | Future major version |

### Security Features

✅ **Secret Scanning** - Enabled (detects API keys, tokens, credentials)  
✅ **Security Advisories** - Enabled (private vulnerability reporting)  
⚙️ **Dependabot** - Configurable (automated dependency updates)  
🔍 **Code Scanning** - Setup available (CodeQL analysis)  
🛡️ **Branch Protection** - Configurable (with status checks)  

**Setup Instructions**: See [docs/SECURITY_CONFIGURATION.md](SECURITY_CONFIGURATION.md)

---

## Code Quality Standards

### Quality Metrics

Our project maintains these quality standards:

```yaml
Code Coverage:
  Overall: ≥ 80%
  Critical Paths: ≥ 90%
  New Code: ≥ 85%

Code Quality:
  Duplicated Lines: < 5%
  Cognitive Complexity: < 10 per function
  Cyclomatic Complexity: < 8 per function
  
Security Issues:
  Critical: 0 allowed
  High: 0 allowed
  Medium: ≤ 2 allowed (with justification)
  Low: ≤ 5 allowed

Ratings:
  Maintainability: A
  Reliability: A
  Security: A
```

### Quality Tools

| Tool | Purpose | Type |
|------|---------|------|
| **SonarQube** | Code quality + security analysis | SAST |
| **Checkstyle** | Code style enforcement | Linter |
| **SpotBugs** | Bug detection + security | Static Analysis |
| **PMD** | Code smell detection | Static Analysis |
| **JaCoCo** | Code coverage measurement | Coverage |
| **OWASP Dependency-Check** | Vulnerable dependencies | Dependency Check |

### Running Quality Checks

```bash
# All checks (tests + coverage + quality)
mvn clean verify sonar:sonar

# Individual tools
mvn checkstyle:check      # Style
mvn spotbugs:check        # Bugs
mvn pmd:check            # Smells
mvn dependency-check:check  # Vulnerabilities

# View coverage report
open target/site/jacoco/index.html
```

### SonarQube Integration

- **Server**: SonarCloud (cloud-based, no setup needed)
- **Integration**: Maven + GitHub Actions
- **Quality Gate**: Enforced on develop → main PRs
- **Reports**: Available at https://sonarcloud.io/

**Setup Instructions**: See [docs/SECURITY_CONFIGURATION.md#sonarqube-integration](SECURITY_CONFIGURATION.md#sonarqube-integration)

---

## Getting Started

### Prerequisites

- Java 21 LTS
- Maven 3.6+
- Git 2.39+
- GitHub account

### Initial Setup

```bash
# Clone repository
git clone https://github.com/hennamusick/java-rest-assured.git
cd java-rest-assured

# Install dependencies
mvn clean install

# Run tests
mvn test

# View test report
open allure-results/index.html
```

### Branch Strategy

**Main Branches**:
- `main` - Production release branch
- `develop` - Development integration branch

**Feature Branches**:
```bash
# Create feature branch
git checkout -b feature/your-feature-name develop

# Work on feature
git add .
git commit -m "feat: your feature description"

# Push and create PR
git push origin feature/your-feature-name
```

### First Test Run

```bash
# Run a single test
mvn test -Dtest=YourTestClass

# Run tests matching pattern
mvn test -Dtest=*RestApiTest

# Run with specific TestNG suite
mvn test -Dsuite=regression-testing.xml

# View Allure report
mvn allure:serve
```

---

## Contribution Guidelines

### Code Standards

Before committing, ensure:

✅ Code passes all quality checks:
```bash
mvn clean verify sonar:sonar
```

✅ Code follows style guidelines:
```bash
mvn checkstyle:check
```

✅ Code has adequate coverage:
```bash
mvn jacoco:report
# Verify ≥ 80% coverage
```

✅ No security vulnerabilities:
```bash
mvn dependency-check:check
mvn spotbugs:check
```

### Commit Message Format

Follow conventional commits:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types**: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

**Examples**:
```
feat(api): add user authentication endpoint
fix(tests): resolve flaky timeout assertions
docs: update README with new examples
test: add integration tests for payment API
```

### Pull Request Process

1. **Create PR** from feature branch to `develop`
2. **Pass Checks**:
   - ✅ Java CI/CD Pipeline
   - ✅ SonarCloud Quality Gate
   - ✅ Code Coverage ≥ 80%
   - ✅ Security Scanning
3. **Code Review**: Minimum 1 approval required
4. **Merge**: Use "Squash and merge" for clean history

### Code Review Checklist

Reviewers verify:

- ✅ Code quality (SonarCloud gate passes)
- ✅ Test coverage (≥ 80%)
- ✅ Security (no vulnerabilities)
- ✅ Style (Checkstyle passes)
- ✅ Tests are meaningful and passing
- ✅ Documentation is updated

---

## CI/CD Pipelines

### GitHub Actions Workflows

#### 1. Java Build Pipeline (java-build.yml)

**Triggers**: Push to any branch, Pull Request

**Steps**:
1. Checkout code
2. Setup Java 21 + Maven cache
3. Build and compile
4. Run tests with Allure reporting
5. Run quality analysis (SonarQube)
6. Check quality gate
7. Deploy to GitHub Pages

**Status Checks Required**:
- ✅ Java CI/CD Pipeline
- ✅ SonarCloud Quality Gate
- ✅ Code Coverage ≥ 80%

#### 2. Auto-PR Workflow (auto-pr-develop-to-main.yml)

**Triggers**: Push to `develop` branch

**Function**: Automatically creates PR from `develop` → `main`

**Features**:
- Idempotent (no duplicate PRs)
- Updates existing PR if branch changes
- Uses peter-evans/create-pull-request action

**View Status**: GitHub → Pull Requests

### Monitoring Workflows

**Check workflow status**:
```bash
# View all workflows
gh workflow list

# View specific workflow runs
gh workflow run java-build.yml --list

# View workflow logs
gh run view <run-id> --log
```

### Troubleshooting Workflows

**Pipeline Failing?**

1. Check GitHub Actions logs: Settings → Actions → All workflows
2. Review error messages in the run details
3. Common issues:
   - Tests failing locally? Run `mvn clean test`
   - Coverage below 80%? Add more tests
   - Quality gate failing? Run `mvn sonar:sonar` locally

---

## Troubleshooting

### Common Issues

#### Build Fails with "Maven not found"

```bash
# Verify Maven installation
mvn --version

# Install Maven if needed
brew install maven
```

#### Tests Fail Locally but Pass in CI

```bash
# Clean build
mvn clean install

# Run with same settings as CI
mvn -T 1C clean verify

# Check for timing issues
mvn test -Dgroups=fast
```

#### SonarQube Quality Gate Fails

```bash
# Run locally to see issues
mvn clean verify sonar:sonar

# View details at https://sonarcloud.io/
# Fix issues in code
# Commit and push (workflow will rerun)
```

#### Code Coverage Below 80%

```bash
# Generate coverage report
mvn clean test jacoco:report

# View report
open target/site/jacoco/index.html

# Add tests for uncovered code
# Verify coverage with: mvn clean test jacoco:report
```

#### Checkstyle Violations

```bash
# Check for violations
mvn checkstyle:check

# View details
open target/checkstyle-result.xml

# Fix violations in code
mvn checkstyle:check  # Re-run to verify
```

### Getting Help

- **GitHub Issues**: Create an issue for bugs/feature requests
- **Documentation**: Check [docs/SECURITY_CONFIGURATION.md](SECURITY_CONFIGURATION.md)
- **Code Examples**: Review existing test cases in `src/test/java/`

---

## Resources

### Official Documentation

- [REST Assured Docs](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/)
- [Allure Report](https://docs.qameta.io/allure/)
- [GitHub Actions](https://docs.github.com/en/actions)
- [SonarQube Docs](https://docs.sonarqube.org/)

### Security & Quality

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [CWE Top 25](https://cwe.mitre.org/top25/)
- [NIST Framework](https://www.nist.gov/cyberframework)
- [GitHub Security](https://docs.github.com/en/code-security)
- [Checkstyle Rules](https://checkstyle.sourceforge.io/)
- [SpotBugs Manual](https://spotbugs.github.io/)
- [PMD Rules](https://pmd.github.io/latest/pmd_rules_java.html)

### Best Practices

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Effective Java](https://www.oreilly.com/library/view/effective-java-3rd/9780134685991/)
- [REST API Design Best Practices](https://restfulapi.net/)
- [API Testing Best Practices](https://testautomationresources.com/rest-api-testing/)

### Tools & Services

- [SonarCloud](https://sonarcloud.io/) - Cloud code quality
- [GitHub Security Dashboard](https://github.com/hennamusick/java-rest-assured/security)
- [Allure TestOps](https://docs.qameta.io/allure-testops/) - Test management
- [Maven Central Repository](https://mvnrepository.com/)

---

## Quick Links

| Resource | Link |
|----------|------|
| **Repository** | https://github.com/hennamusick/java-rest-assured |
| **Issues** | https://github.com/hennamusick/java-rest-assured/issues |
| **Pull Requests** | https://github.com/hennamusick/java-rest-assured/pulls |
| **Security Advisories** | https://github.com/hennamusick/java-rest-assured/security/advisories |
| **Test Reports** | https://hennamusick.github.io/java-rest-assured/ |
| **SonarCloud** | https://sonarcloud.io/dashboard?id=hennamusick_java-rest-assured |
| **GitHub Actions** | https://github.com/hennamusick/java-rest-assured/actions |

---

## Contact & Support

- **Project Owner**: @hennamusick
- **Code Owners**: See [.github/CODEOWNERS](.github/CODEOWNERS)
- **Security Issues**: Use private advisory system (Settings → Security)
- **Questions**: Create an issue or start a discussion

---

**Last Updated**: January 3, 2026  
**Version**: 1.0  
**Status**: ✅ Active Development
