package org.talan.jenkins

class ScmHelper {
    def scmVars
    def params
    def isPullRequest
    def commitSha
    def buildBranch
    def pullId
    def lastCommitAuthorEmail
    def origin
    def repo
    def org
    def sonarToken
    def githubToken

    ScmHelper(scmVars, params) {
        this.scmVars = scmVars
        this.params = params
    }
    
    collectBuildInfo() {
        sh 'echo Hi'
    }
}
