pipeline {
  agent any
  stages {
    stage('package') {
      steps {
        sh 'mvn clean package -Dmaven.test.skip=true'
      }
    }
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv(installationName: 'Sonar') { // You can override the credential to be used
          sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
        }
      }
    }
  }
}
