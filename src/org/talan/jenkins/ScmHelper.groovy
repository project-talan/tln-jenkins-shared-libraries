package org.talan.jenkins

class ScmHelper {
    def script
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

    ScmHelper(script, scmVars, params) {
        this.steps = script
        this.scmVars = scmVars
        this.params = params
    }
    
    public void collectBuildInfo() {
        this.script.sh('echo Hi && pwd')
    }
}
