pipeline {
  agent any
  stages {
    stage('package') {
      steps {
        sh 'mvn clean package -Dmaven.test.skip=true'
      }
    }
  }
}
