pipeline {
  agent any
  stages {
    stage('SonarQube analysis') {
    withSonarQubeEnv(credentialsId: 'feae91213da1a38b6b371919db51f75d92d74fa4', installationName:'Sonar') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
    }
  }
  }
}
