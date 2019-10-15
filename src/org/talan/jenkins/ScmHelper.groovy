package org.talan.jenkins

class scmHelper {
  def script

  scmHelper(script) {
    this.script = script
  }
  
  public void hi() {
    this.script.sh('echo Hi from class')
  }
}