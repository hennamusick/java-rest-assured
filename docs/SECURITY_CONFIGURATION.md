# 🔒 Security Configuration Guide

## Table of Contents
- [GitHub Security Setup](#github-security-setup)
- [Code Quality Standards](#code-quality-standards)
- [SonarQube Integration](#sonarqube-integration)
- [Branch Protection Rules](#branch-protection-rules)
- [Enabling Code Scanning](#enabling-code-scanning)
- [Dependabot Configuration](#dependabot-configuration)
- [Secret Scanning](#secret-scanning)
- [Security Advisories](#security-advisories)

---

## GitHub Security Setup

### Step 1: Navigate to Security Settings

```
GitHub Repository → Settings → Code security & analysis
```

### Step 2: Review Current Status

Check the status of each security feature:
- ✅ Secret scanning - ENABLED
- ⚠️ Dependabot alerts - DISABLED (can enable)
- ⚠️ Code scanning - NOT SET UP
- 📋 Private vulnerability reporting - DISABLED

---

## Code Quality Standards

### Overview of Quality Tools

| Tool | Purpose | Type | Language |
|------|---------|------|----------|
| **SonarQube** | Code quality, security, coverage | SAST + Metrics | Java, Python, JS |
| **Checkstyle** | Style & convention enforcement | Linter | Java |
| **Spotbugs** | Bug detection (null, synchronization) | Static Analysis | Java |
| **PMD** | Code smell & pattern detection | Static Analysis | Java |
| **Jacoco** | Code coverage measurement | Coverage | Java |
| **OWASP Dependency** | Vulnerable dependency detection | Dependency Check | All |
| **Fortify SCA** | Enterprise SAST (advanced) | SAST | Java, Python, JS |
| **Black Duck** | License & vulnerability scanning | Dependency Check | All |

### Quality Metrics Target

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

Test Quality:
  Test Success Rate: 100%
  Test Execution Time: < 5 minutes
  Flaky Tests: 0 allowed
```

---

## SonarQube Integration

### Step 1: Setup SonarQube Server

#### Option A: SonarCloud (Recommended for GitHub)

```bash
# Visit https://sonarcloud.io
# Sign in with GitHub account
# Authorize SonarCloud
# Create organization (matches GitHub org)
# Import repository
```

#### Option B: Self-Hosted SonarQube

```bash
# Using Docker
docker run -d --name sonarqube \
  -p 9000:9000 \
  -e SONAR_JDBC_URL=jdbc:postgresql://db:5432/sonar \
  -e SONAR_JDBC_USERNAME=sonar \
  -e SONAR_JDBC_PASSWORD=sonar \
  sonarqube:latest

# Access: http://localhost:9000
# Default: admin/admin
```

### Step 2: Create SonarQube Token

**SonarCloud/SonarQube UI → My Account → Security → Tokens**

```bash
Token Name: GitHub Actions
Expiration: 1 year (renewable)
```

### Step 3: Add to GitHub Secrets

```
Settings → Secrets and variables → Actions
  New repository secret:
    Name: SONAR_TOKEN
    Value: squ_xxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

### Step 4: Configure Maven for SonarQube

Add to `pom.xml`:

```xml
<project>
  <properties>
    <!-- SonarQube Configuration -->
    <sonar.projectKey>hennamusick_java-rest-assured</sonar.projectKey>
    <sonar.organization>hennamusick</sonar.organization>
    <sonar.host.url>https://sonarcloud.io</sonar.host.url>
    <sonar.login>${env.SONAR_TOKEN}</sonar.login>
    
    <!-- Code Coverage -->
    <sonar.coverage.jacoco.xmlReportPaths>
      ${project.basedir}/target/site/jacoco/jacoco.xml
    </sonar.coverage.jacoco.xmlReportPaths>
    
    <!-- Quality Gates -->
    <sonar.qualitygate.wait>true</sonar.qualitygate.wait>
    <sonar.qualitygate.timeout>300</sonar.qualitygate.timeout>
  </properties>

  <build>
    <plugins>
      <!-- JaCoCo for Code Coverage -->
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.10</version>
        <executions>
          <execution>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
          </execution>
          <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
              <goal>report</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

      <!-- SonarQube Scanner -->
      <plugin>
        <groupId>org.sonarsource.scanner.maven</groupId>
        <artifactId>sonar-maven-plugin</artifactId>
        <version>3.9.1.2184</version>
      </plugin>

      <!-- Checkstyle for Code Style -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-checkstyle-plugin</artifactId>
        <version>3.3.1</version>
        <configuration>
          <configLocation>google_checks.xml</configLocation>
          <includeTestSourceDirectory>true</includeTestSourceDirectory>
          <violationSeverity>error</violationSeverity>
          <failOnViolation>false</failOnViolation>
        </configuration>
        <executions>
          <execution>
            <phase>verify</phase>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

      <!-- SpotBugs for Bug Detection -->
      <plugin>
        <groupId>com.github.spotbugs</groupId>
        <artifactId>spotbugs-maven-plugin</artifactId>
        <version>4.7.3.5</version>
        <configuration>
          <effort>max</effort>
          <threshold>medium</threshold>
          <failOnError>false</failOnError>
          <plugins>
            <plugin>
              <groupId>com.h3xstream.findsecbugs</groupId>
              <artifactId>findsecbugs-plugin</artifactId>
              <version>1.12.0</version>
            </plugin>
          </plugins>
        </configuration>
        <executions>
          <execution>
            <phase>verify</phase>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

      <!-- PMD for Code Smells -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.21.0</version>
        <configuration>
          <sourceEncoding>utf-8</sourceEncoding>
          <minimumTokens>100</minimumTokens>
          <linkXRef>false</linkXRef>
          <failOnViolation>false</failOnViolation>
          <rulesets>
            <ruleset>rulesets/java/quickstart.xml</ruleset>
          </rulesets>
        </configuration>
        <executions>
          <execution>
            <phase>verify</phase>
            <goals>
              <goal>check</goal>
              <goal>cpd-check</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

      <!-- OWASP Dependency Check -->
      <plugin>
        <groupId>org.owasp</groupId>
        <artifactId>dependency-check-maven</artifactId>
        <version>8.4.2</version>
        <configuration>
          <format>JSON</format>
          <failBuildOnAnyVulnerability>false</failBuildOnAnyVulnerability>
          <suppressionFile>${project.basedir}/dependency-check-suppressions.xml</suppressionFile>
        </configuration>
        <executions>
          <execution>
            <phase>verify</phase>
            <goals>
              <goal>check</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

### Step 5: Add SonarQube to GitHub Actions Workflow

Update `.github/workflows/java-build.yml`:

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0  # Required for SonarQube

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2

      - name: Run tests with coverage
        run: |
          mvn clean verify \
            -DskipITs=false

      - name: Run Code Quality Analysis
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          mvn sonar:sonar \
            -Dsonar.projectKey=hennamusick_java-rest-assured \
            -Dsonar.organization=hennamusick \
            -Dsonar.host.url=https://sonarcloud.io \
            -Dsonar.login=$SONAR_TOKEN

      - name: Check Quality Gate
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: |
          echo "Quality gate status will be checked by SonarCloud"
```

### Step 6: Configure Quality Gate Rules

**SonarCloud UI → Project Settings → Quality Gate**

```yaml
Quality Gate Name: java-rest-assured

Conditions:
  ✅ Coverage: ≥ 80%
  ✅ Duplicated Lines Density: < 5%
  ✅ Security Issues: = 0
  ✅ Maintainability Rating: A
  ✅ Reliability Rating: A
  ✅ Security Rating: A
  ✅ Security Hotspots Reviewed: = 100%
```

### Step 7: Setup Quality Gate Status in GitHub

**SonarCloud UI → Administration → General Settings**

```yaml
GitHub:
  ✅ GitHub App for Checks
  ✅ Set to "Fail" if Quality Gate fails
  ✅ Comment PR with analysis summary
```

---

## Branch Protection Rules

### Enable Branch Protection for `main`

**Path:** Settings → Branches → Branch protection rules → Add rule

#### Configuration:

```yaml
Branch pattern: main

Protection Rules:
  ✅ Require a pull request before merging
     ├─ Require approvals: 1
     └─ Dismiss stale PR approvals: YES
  
  ✅ Require status checks to pass before merging
     ├─ Require branches to be up to date before merging: YES
     └─ Required status checks:
        ├─ Java CI/CD Pipeline
        ├─ SonarCloud Quality Gate
        ├─ Code Coverage (≥80%)
        └─ Security Scanning
  
  ✅ Require conversation resolution before merging
  
  ✅ Restrict who can push to matching branches
  
  ✅ Allow force pushes: NO
  
  ✅ Allow deletions: NO
```

### Enable Branch Protection for `develop`

**Path:** Settings → Branches → Branch protection rules → Add rule

#### Configuration:

```yaml
Branch pattern: develop

Protection Rules:
  ✅ Require a pull request before merging
     └─ Require approvals: 0 (optional)
  
  ✅ Require status checks to pass before merging
     ├─ Require branches to be up to date before merging: YES
     └─ Required status checks:
        └─ Java CI/CD Pipeline
  
  ✅ Dismiss stale PR approvals: YES
  
  ✅ Allow force pushes: NO
```

---

## Enabling Code Scanning

### Option 1: GitHub Code Scanning (CodeQL)

**Path:** Settings → Code security & analysis → Code scanning → Set up code scanning

#### Steps:

1. Click **"Set up code scanning"**
2. Choose **"CodeQL Analysis"**
3. Select **"Set up this workflow"**
4. Review the suggested `.github/workflows/codeql.yml`
5. Click **"Commit changes"**

#### Benefits:

- Scans for SQL injection, XSS, and other vulnerabilities
- Runs on every push and pull request
- Free for public repositories
- Integrates with GitHub UI

### Option 2: Additional SAST & Quality Tools

#### SonarQube (Recommended)
See [SonarQube Integration](#sonarqube-integration) section above.

#### Third-party Tools
- **Snyk** - Dependency & code scanning, vulnerability reporting
- **Checkmarx** - Advanced SAST, CWE mapping, remediation guidance
- **Fortify SCA** - Enterprise SAST, compliance reporting
- **Black Duck** - License compliance, vulnerability detection
- **Veracode** - Binary scanning, dynamic analysis

---

## Dependabot Configuration

### Step 1: Enable Dependabot Alerts

**Path:** Settings → Code security & analysis → Dependabot alerts

```
✅ Enable Dependabot alerts
```

### Step 2: Enable Dependabot Security Updates

**Path:** Settings → Code security & analysis → Dependabot security updates

```
✅ Enable Dependabot security updates
```

### Step 3: Create Dependabot Configuration

Create `.github/dependabot.yml`:

```yaml
version: 2
updates:
  # Maven dependencies
  - package-ecosystem: "maven"
    directory: "/"
    schedule:
      interval: "weekly"
      day: "monday"
      time: "03:00"
    open-pull-requests-limit: 5
    labels:
      - "dependencies"
      - "security"
    reviewers:
      - "hennamusick"
    assignees:
      - "hennamusick"
    commit-message:
      prefix: "chore(deps)"
      include: "scope"
    pull-request-branch-name:
      separator: "/"
    allow:
      - dependency-type: "direct"
      - dependency-type: "indirect"
    ignore:
      # Add dependencies to ignore if needed
      # - dependency-name: "log4j"

  # GitHub Actions
  - package-ecosystem: "github-actions"
    directory: "/"
    schedule:
      interval: "weekly"
      day: "monday"
    open-pull-requests-limit: 5
    labels:
      - "ci/cd"
    commit-message:
      prefix: "ci(actions)"
```

### Step 4: Configure Auto-Merge (Optional)

For minor dependency updates:

```bash
# In GitHub CLI or web UI
gh repo edit --enable-auto-merge
```

---

## Secret Scanning

### Already Enabled ✅

Secret scanning is active and will detect:
- GitHub tokens
- AWS access keys
- Private SSH keys
- Database passwords
- API keys and tokens
- OAuth tokens

### What Happens When Secret Detected:

1. **Alert:** Repository admin receives notification
2. **Action Required:** Secret must be rotated immediately
3. **Remediation:**
   - Generate new credentials
   - Revoke old credentials
   - Remove from git history (if possible)

### Best Practices:

```
❌ DON'T commit secrets to git
✅ DO use environment variables
✅ DO use GitHub Secrets for CI/CD
✅ DO rotate credentials regularly
✅ DO scan for secrets before pushing
```

---

## Security Advisories

### Reporting a Vulnerability

**Path:** Security → Report a vulnerability

Users can privately report vulnerabilities using GitHub's built-in system.

### Publishing an Advisory

When you identify or fix a vulnerability:

1. Go to **Security → Advisories**
2. Click **"New draft security advisory"**
3. Fill in:
   - Vulnerability description
   - CVE (if available)
   - Affected versions
   - Patched versions
   - Severity rating
   - References
4. Click **"Create draft advisory"**
5. Review and publish

---

## Maven Security Configuration

### Already Configured in pom.xml ✅

```xml
<!-- Use HTTPS for all repositories -->
<repositories>
  <repository>
    <id>central</id>
    <url>https://repo.maven.apache.org/maven2</url>
  </repository>
</repositories>

<!-- Plugin repository with HTTPS -->
<pluginRepositories>
  <pluginRepository>
    <id>central</id>
    <url>https://repo.maven.apache.org/maven2</url>
  </pluginRepository>
</pluginRepositories>

<!-- Enforce checksum verification -->
<configuration>
  <checksumPolicy>fail</checksumPolicy>
</configuration>
```

### Additional Maven Security Steps:

#### 1. Enable GPG Signing (Optional)

```bash
# Install GPG
brew install gnupg

# Generate key
gpg --gen-key

# Configure Maven
# Edit ~/.m2/settings.xml
<gpg>
  <passphrase>${gpg.passphrase}</passphrase>
</gpg>
```

#### 2. Regular Dependency Audit

```bash
# Check for vulnerable dependencies
mvn dependency-check:check

# See CVE details
mvn dependency-check:aggregate
```

#### 3. Enforce Dependency Versions

```bash
# Fail build if dependency not pinned
mvn dependency:analyze-duplicate
mvn dependency:analyze-only
```

---

## GitHub Actions Security

### Secrets Management

#### Create Repository Secrets:

**Settings → Secrets and variables → Actions → New repository secret**

```
Name: MAVEN_GPG_PASSPHRASE
Value: [your passphrase]

Name: SONAR_TOKEN
Value: [token from SonarQube]

Name: SNYK_TOKEN
Value: [token from Snyk]
```

#### Use in Workflows:

```yaml
- name: Secure Operation
  env:
    SECRET_TOKEN: ${{ secrets.MY_SECRET }}
  run: |
    # Secret automatically masked in logs
    ./script-using-secret.sh
```

### Workflow Permissions

Set minimum required permissions:

```yaml
jobs:
  build:
    permissions:
      contents: read      # Can read code
      checks: write       # Can write check statuses
      pull-requests: write # Can write to PRs
```

---

## Security Checklist

### Pre-Release

- [ ] All tests passing
- [ ] Code review completed
- [ ] No security warnings from code scanning
- [ ] No high/critical vulnerabilities in dependencies
- [ ] Branch protection rules enforced
- [ ] Changelog updated
- [ ] Security policy reviewed

### Post-Release

- [ ] Release notes published
- [ ] Security advisories updated
- [ ] Dependabot PRs merged
- [ ] Monitoring for reported issues

### Ongoing

- [ ] Weekly dependency updates reviewed
- [ ] Monthly security scanning
- [ ] Quarterly security policy review
- [ ] Annual penetration testing (for enterprise)

---

## Monitoring & Response

### GitHub Security Dashboard

**Settings → Security & analysis → Overview**

Shows:
- 📊 Dependency vulnerabilities
- 🔍 Code scanning alerts
- 🔐 Secret scanning alerts
- 📢 Security advisories

### Setting Up Alerts

```
Settings → Code security & analysis
  ├─ Dependabot alerts: ✅
  ├─ Dependabot security updates: ✅
  ├─ Code scanning alerts: ✅
  └─ Secret scanning alerts: ✅
```

### Email Notifications

GitHub will email when:
- New vulnerability detected
- Secret scanning finds a secret
- Code scanning finds an issue
- Dependabot can't create a PR

---

## Resources

### Code Quality Standards
- [SonarQube Documentation](https://docs.sonarqube.org/)
- [SonarCloud for GitHub](https://sonarcloud.io/)
- [Checkstyle Configuration](https://checkstyle.sourceforge.io/)
- [SpotBugs Maven Plugin](https://spotbugs.github.io/)
- [PMD Rules](https://pmd.github.io/latest/pmd_rules_java.html)
- [JaCoCo Code Coverage](https://www.jacoco.org/)

### Security Standards
- [GitHub Security Docs](https://docs.github.com/en/code-security)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [CWE Top 25](https://cwe.mitre.org/top25/)
- [SANS Security](https://www.sans.org/reading-room/whitepapers)
- [NIST Cybersecurity Framework](https://www.nist.gov/cyberframework)
- [CIS Benchmarks](https://www.cisecurity.org/benchmarks)

---

## Quick Start Commands

### Run All Quality Checks Locally

```bash
# Full analysis (tests + coverage + quality checks)
mvn clean verify sonar:sonar -Dsonar.projectKey=hennamusick_java-rest-assured

# Just tests and coverage
mvn clean test jacoco:report

# Just SonarQube analysis (requires prior test run)
mvn sonar:sonar

# Just checkstyle
mvn checkstyle:check

# Just spotbugs
mvn spotbugs:check

# Just PMD
mvn pmd:check

# Just dependency check
mvn dependency-check:check

# Generate quality report
mvn site
```

### View Reports Locally

```bash
# Code coverage
open target/site/jacoco/index.html

# Checkstyle report
open target/site/checkstyle.html

# SpotBugs report
open target/spotbugsXml.xml

# PMD report
open target/site/pmd.html

# Full site report
open target/site/index.html
```

---

**Last Updated:** January 3, 2026
**Version:** 2.0 (Added SonarQube & Code Quality Standards)
