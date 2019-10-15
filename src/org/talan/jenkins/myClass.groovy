package org.talan.jenkins

class myClass {
  def script

  myClass(script) {
    this.script = script
  }
  
  public void hi() {
    this.script.sh('echo Hi from class')
  }
}