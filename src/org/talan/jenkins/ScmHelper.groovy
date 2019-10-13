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

  ScmHelper(script, sonarToken, githubToken) {
    this.script = script
    this.scmVars = [:]
    this.params = [:]
    this.isPullRequest = false
    this.commitSha = ''
    this.buildBranch = ''
    this.pullId = ''
    this.lastCommitAuthorEmail = ''
    this.origin = ''
    this.repo = ''
    this.org = ''
    this.sonarToken = sonarToken
    this.githubToken = githubToken
  }
  
  public void printTopic(topic, width = 80) {
    println("[*] ${topic} ".padRight(width, '-'))
  }
    
  public void collectBuildInfo(scmVars, params) {
    this.scmVars = scmVars
    this.params = params
    //
    this.commitSha = this.scmVars.GIT_COMMIT
    this.buildBranch = this.scmVars.GIT_BRANCH
    if (this.buildBranch.contains('PR-')) {
      // multibranch PR build
      this.isPullRequest = true
      this.pullId = this.script.env.CHANGE_ID
    } else if (this.params.containsKey('sha1')){
      // standard PR build
      this.isPullRequest = true
      this.pullId = this.params.ghprbPullId
      this.commitSha = this.params.ghprbActualCommit
    } else {
      // PUSH build
    }
    //
    printTopic('SCM variables')
    println(this.scmVars)
    printTopic('Job input parameters');
    println(this.params)
    //
    // Be able to work with standard pipeline and multibranch pipeline identically
    printTopic('Build info')
    println("[PR:${this.isPullRequest}] [BRANCH:${this.buildBranch}] [COMMIT: ${this.commitSha}] [PULL ID: ${this.pullId}]")
    printTopic('Environment variables')
    this.script.sh(script:'env', returnStdout: true)
    //
    // Extract organisation and repository names
    printTopic('Repo parameters')
    this.origin = this.script.sh(script: 'git config --get remote.origin.url', returnStdout: true)
    this.org = this.script.sh(script: '''git config --get remote.origin.url | rev | awk -F'[./:]' '{print $2}' | rev''', returnStdout: true).trim()
    this.repo = this.script.sh(script: '''git config --get remote.origin.url | rev | awk -F'[./:]' '{print $1}' | rev''', returnStdout: true).trim()
    println("[origin:${this.origin}] [org:${this.org}] [repo:${this.repo}]")
    //
    // Get authors' emails
    printTopic('Author(s)')
    this.lastCommitAuthorEmail = this.script.sh(script: '''git log --format="%ae" HEAD^!''', returnStdout: true).trim()
    if (!this.isPullRequest){
      this.lastCommitAuthorEmail = this.script.sh(script: '''git log -2 --format="%ae" | paste -s -d ",\n"''', returnStdout: true).trim()
    }
    println("[lastCommitAuthorEmail:${this.lastCommitAuthorEmail}]")
    //
    //
    printTopic('Sonarqube properties')
    this.script.sh(script: 'cat sonar-project.properties', returnStdout: true)

    this.script.sh('echo Hi && pwd')
  }

  /*
   * params:

   * @org:
   * @repo:
   * @token:
   * @context:
   * @description:
   * @target_url:
   * @state: error, failure, pending, or success
   * @sha:
   */
  public void setGithubBuildStatus(context, description, target_url, state) {
    this.script.sh("curl -s 'https://api.github.com/repos/${this.org}/${this.repo}/statuses/${this.commitSha}?access_token=${this.githubToken}' -H 'Content-Type: application/json' -X POST -d '{\"state\": \"${state}\", \"description\": \"${description}\", \"target_url\": \"${target_url}\", \"context\": \"${context}\" }'")
    /*
    step([
      $class: "GitHubCommitStatusSetter",
      //reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/<your-repo-url>"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: context],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      commitShaSource: [$class: "ManuallyEnteredShaSource", sha: sha ],
      statusBackrefSource: [$class: "ManuallyEnteredBackrefSource", backref: target_url],
      statusResultSource: [$class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: description, state: state]] ]
    ]);
    */
  }

  /*
   *
   */
  public void sendEmailNotification(subj, body, recepients = '') {
    this.script.emailext(body: body,
      recipientProviders: [
        [$class: 'CulpritsRecipientProvider'],
        [$class: 'DevelopersRecipientProvider'],
        [$class: 'RequesterRecipientProvider']
      ],
      subject: subj,
      to: [this.lastCommitAuthorEmail, recepients].join(',')
    )
  }

}
