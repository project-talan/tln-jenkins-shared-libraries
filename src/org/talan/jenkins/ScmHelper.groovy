package org.talan.jenkins

class ScmHelper {
    def steps
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

    ScmHelper(steps, scmVars, params) {
        this.steps = steps
        this.scmVars = scmVars
        this.params = params
    }
    
    public void collectBuildInfo() {
        steps.sh('echo Hi && pwd')
    }
}
