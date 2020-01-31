pipeline {
  agent any
  stages {
    stage('SonarQube analysis') {
    steps {
        withSonarQubeEnv(installationName:'Sonar') { // You can override the credential to be used
        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
    }
    }
  }
  }
}
