import jenkins.model.Jenkins
import hudson.model.Build
import hudson.model.Result
import hudson.tasks.junit.CaseResult
import hudson.tasks.junit.SuiteResult

// Function to parse JUnit XML test results and return a map of test case details
def parseJUnitResults(build) {
    def junitResult = build.getAction(hudson.tasks.junit.TestResultAction.class)
    def testResultsMap = [:]

    if (junitResult != null) {
        junitResult.getResult().getSuites().each { SuiteResult suiteResult ->
            suiteResult.getCases().each { CaseResult caseResult ->
                def testCaseName = caseResult.getFullName()
                def testStatus = caseResult.getStatus()
                def errorMessage = caseResult.getErrorStackTrace() ?: ""

                testResultsMap[testCaseName] = [status: testStatus, error: errorMessage]
            }
        }
    }
    return testResultsMap
}

def build = currentBuild.getRawBuild()
def buildNumber = build.number
def buildStatus = build.resultIsBetterOrEqualTo(Result.SUCCESS) ? "SUCCESS" : "FAILURE"

def jobName = build.getParent().getName()
def buildUrl = Jenkins.instance.rootUrl + jobName + "/" + buildNumber + "/"
def emailSubject = "Jenkins Build #${buildNumber} - ${buildStatus}"

// Parse the JUnit test results
def testResults = parseJUnitResults(build)

// Count the number of passed and failed test cases
def passedTests = testResults.count { key, value -> value.status == "PASSED" }
def failedTests = testResults.count { key, value -> value.status == "FAILED" }

// Build the email body
def emailBody = """
    <p>Build Information:</p>
    <ul>
        <li>Job: ${jobName}</li>
        <li>Build Number: ${buildNumber}</li>
        <li>Build Status: ${buildStatus}</li>
        <li>Build URL: <a href="${buildUrl}">${buildUrl}</a></li>
    </ul>

    <p>Test Results:</p>
    <table border="1">
        <tr>
            <th>Test Case</th>
            <th>Status</th>
            <th>Error Message</th>
        </tr>
"""

testResults.each { testCase, details ->
    emailBody += """
        <tr>
            <td>${testCase}</td>
            <td>${details.status}</td>
            <td>${details.error}</td>
        </tr>
    """
}

emailBody += """
    </table>
    <p>Total Passed Tests: ${passedTests}</p>
    <p>Total Failed Tests: ${failedTests}</p>
"""

emailext(
    subject: emailSubject,
    body: emailBody,
    to: 'recipient@example.com', // Add your recipient's email address here
    mimeType: 'text/html'
)
