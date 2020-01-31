pipeline {
  agent any
  stages {
    stage('myStage'){
      steps {
        sh 'ls -la' 
      }
    }
    stage("build & SonarQube analysis") {
            agent any
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=ecoBio -Dsonar.host.url=htpp://localhost:9000 -Dsonar.login=a2346971a7b31ddb31d67d3d769bb9f153c21ad4' 
              }
       }
  }
}
