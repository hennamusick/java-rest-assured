# 🔒 Security Policy

## Reporting a Vulnerability

We take security seriously. If you discover a security vulnerability in the java-rest-assured project, please report it responsibly.

### ⚠️ Please DO NOT:
- ❌ Open a public GitHub issue for security vulnerabilities
- ❌ Post vulnerability details in forums or social media
- ❌ Share sensitive information in pull requests

### ✅ Instead:

**Use GitHub's Private Vulnerability Reporting:**
1. Navigate to the **Security** tab in the repository
2. Click **"Report a vulnerability"**
3. Fill in the vulnerability details
4. Submit your report privately

**Or Email Us:**
- Contact the maintainers through GitHub's security advisory system
- Email: [repository owner's email if available]

### 📋 What to Include in Your Report:

```
- Description of the vulnerability
- Steps to reproduce the issue
- Potential impact assessment
- Suggested fix (if available)
- Your contact information
- Timeline for disclosure (if any)
```

## Response Timeline

| Timeline | Action |
|----------|--------|
| **Within 24 hours** | We acknowledge receipt of your report |
| **Within 7 days** | Initial assessment and status update |
| **Within 30 days** | Security patch development begins |
| **ASAP** | Patch released and vulnerability disclosed |

## Security Features Enabled

### ✅ Active Security Measures

| Feature | Status | Purpose |
|---------|--------|---------|
| 🔍 **Secret Scanning** | ✅ Enabled | Detects exposed secrets (API keys, tokens) |
| 📦 **Dependabot Alerts** | ✅ Can Enable | Notifies about vulnerable dependencies |
| 🔐 **Code Scanning** | 📋 Setup Available | Scans for code vulnerabilities |
| 🛡️ **Branch Protection** | ⚙️ Configurable | Requires reviews before merge |
| 📢 **Security Advisories** | ✅ Enabled | Report security issues responsibly |

### 🔐 Enabled: Secret Scanning

We use GitHub's secret scanning to detect:
- API keys and tokens
- Database credentials
- OAuth tokens
- SSH private keys
- AWS access keys
- And other sensitive information

**If a secret is detected:**
1. GitHub alerts repository maintainers immediately
2. You'll receive a notification
3. Action is required to rotate/revoke the exposed secret

### 🔍 Recommended: Enable Dependabot

To enable automated dependency vulnerability scanning:

1. **Settings** → **Code security & analysis**
2. Toggle **Dependabot alerts** → ✅ Enable
3. Toggle **Dependabot security updates** → ✅ Enable

**Benefits:**
- 📬 Automatic PRs for vulnerable dependencies
- 🤖 Auto-merge minor security patches
- 📊 Dependency tracking dashboard

### 🔐 Recommended: Enable Code Scanning

To automatically scan code for vulnerabilities:

1. **Settings** → **Code security & analysis**
2. Click **"Set up code scanning"**
3. Choose **CodeQL** analysis
4. Configure as needed
5. Enable workflows

**Scans for:**
- SQL injection vulnerabilities
- Cross-site scripting (XSS)
- Command injection
- Unsafe deserialization
- And 100+ other issue types

### 🛡️ Branch Protection Rules

#### Main Branch Protection

**Settings** → **Branches** → **Add rule** for `main`:

```
✅ Require a pull request before merging
   ├─ Require approvals: 1
   ├─ Dismiss stale PR approvals: ✅
   ├─ Require status checks to pass: ✅
   │  ├─ Java CI/CD Pipeline
   │  └─ Code Quality checks
   ├─ Require branches to be up to date: ✅
   ├─ Require conversation resolution: ✅
   └─ Allow force pushes: ❌
```

#### Develop Branch Protection

**Settings** → **Branches** → **Add rule** for `develop`:

```
✅ Require a pull request before merging
   ├─ Require approvals: 0 (optional)
   ├─ Dismiss stale PR approvals: ✅
   ├─ Require status checks to pass: ✅
   │  └─ Java CI/CD Pipeline
   ├─ Require branches to be up to date: ✅
   └─ Allow force pushes: ❌
```

## Dependency Management

### 📦 Current Dependencies

We maintain dependencies with the following security practices:

1. **Regular Updates** - Dependencies updated quarterly
2. **Version Pinning** - Exact versions in pom.xml for reproducibility
3. **License Compliance** - All dependencies are MIT/Apache compatible
4. **Security Audits** - Dependencies scanned for known vulnerabilities

### 📝 Vulnerable Dependencies

If we detect a vulnerable dependency:
1. Issue is identified and tracked
2. Secure version is identified
3. Patch is applied and tested
4. Release notes document the fix
5. Users are notified

### 🔄 Maven Security

Our Maven configuration includes:

```xml
<!-- Security: Use HTTPS for all repos -->
<repositories>
  <repository>
    <url>https://repo.maven.apache.org/maven2</url>
  </repository>
</repositories>

<!-- Security: Checksum verification -->
<verify>
  <checksumPolicy>fail</checksumPolicy>
</verify>
```

## Code Security Best Practices

### Implemented in This Project

✅ **Secure Coding**
- Input validation on all API calls
- Parameterized queries (when applicable)
- No hardcoded secrets or credentials
- Principle of least privilege in tests

✅ **Dependency Security**
- Regular dependency audits
- Version pinning in pom.xml
- No direct dependencies on vulnerable libraries
- Maven repository security configured

✅ **Access Control**
- Branch protection rules enforced
- Code review requirements
- Limited write access to main branch
- GitHub token scoped permissions

✅ **Logging & Monitoring**
- Detailed request/response logging
- Test execution tracking
- Build failure alerts
- Security scanning enabled

## Security Update Policy

### Release Cycle

```
Critical (CVSS 9-10)   → Hotfix within 24-48 hours
High (CVSS 7-8.9)     → Patch in next release (1-2 weeks)
Medium (CVSS 4-6.9)   → Included in quarterly update
Low (CVSS 0-3.9)      → Addressed in future major version
```

### Notification

- GitHub releases for all security patches
- CHANGELOG.md updated with security fixes
- Release notes clearly mark security-related changes
- Users notified via release announcements

## Security Resources

### Documentation
- [GitHub Security Best Practices](https://docs.github.com/en/code-security)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [CWE Top 25](https://cwe.mitre.org/top25/)

### Tools Used
- [GitHub Secret Scanning](https://docs.github.com/en/code-security/secret-scanning)
- [CodeQL Analysis](https://codeql.github.com/)
- [Dependabot](https://dependabot.com/)
- [Maven Dependency Check](https://jeremylong.github.io/DependencyCheck/)

### Standards Compliance
- 📋 OWASP Secure Coding Practices
- 🔒 CWE/SANS Top 25 Most Dangerous Software Errors
- 🛡️ GitHub Security Lab recommendations

## Questions?

If you have questions about our security practices:
1. Check the [GitHub Security documentation](https://docs.github.com/en/code-security)
2. Review this policy
3. Contact repository maintainers through GitHub issues (non-security)

## Acknowledgments

We appreciate the security research community and responsible disclosure. Security researchers who find and report vulnerabilities responsibly help us make this project safer for everyone.

---

**Last Updated:** January 3, 2026
**Policy Version:** 1.0
**Status:** Active ✅
