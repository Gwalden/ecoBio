// Lien vers Nexus, doit correspondre à l'instance paramétrée dans Jenkins
def nexusId = 'nexus_localhost'

/* *** Configuration de Nexus pour Maven ***/
// URL de Nexus
def nexusUrl = 'http://localhost:8081'
// Repo Id (provient du settings.xml nexus pour récupérer user/password)
def mavenRepoId = 'nexusLocal'

/* *** Repositories Nexus *** */
def nexusRepoSnapbatot = "maven-snapbatots"
def nexusRepoRelease = "maven-releases"



/* *** Détail du projet, récupéré dans le pipeline en lisant le pom.xml *** */
def groupId = ''
def artefactId = ''
def filePath = ''
def packaging = ''
def version = ''

// Variable utilisée pour savoir si c'est une RELEASE ou une SNAPbatOT
def isSnapbatot = true

pipeline {
   agent any

   stages {
      stage('Checkout') {
         steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Gwalden/ecoBio.git']]])
         }
      }
      stage('Get info from POM') {
          steps {
            script {
                pom = readMavenPom file: 'pom.xml'
                groupId = pom.groupId
                artifactId = pom.artifactId
                packaging = pom.packaging
                version = pom.version
                filepath = "target/${artifactId}-${version}.jar"
                isSnapbatot = version.endsWith("-SNAPbatOT")
            }
            echo groupId
            echo artifactId
            echo packaging
            echo version
            echo filepath
            echo "isSnapbatot: ${isSnapbatot}"
          }
      }
      stage('Build') {
          steps {
              bat 'mvn clean package'
          }
      }
      
       stage('tests') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
                 bat "mvn pmd:pmd"
                 bat "mvn pmd:cpd"

			            }
        }
      
	    stage('tests Non Regression') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"


			            }
        }
	       stage('Accès au site') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
    

			            }
        }
	       stage('Upload fichier') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
    

			            }
        }
	       stage('Csv Valide') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
        

			            }
        }
	   
	   	       stage('Fichier Conforme') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
          

			            }
        }
	   
	   
	   	       stage('Fichier non Conforme') {
            steps {
				 bat "mvn checkstyle:checkstyle"
                 bat "mvn spotbugs:spotbugs"
       

			            }
        }
	   
	   
	   	       stage('Accès ancienne données') {
            steps {
				 bat "mvn checkstyle:checkstyle"
 

			            }
        }
      /*
      Ce stage ne se lance que si isSnapbatot est vrai
      Comme on pousse un Snapbatot, on utilise le plugin deploy:deploy-file, cela permet de ne pas mettre les paramètres du Repo dans le pom.xml
      */
      stage('Pubat SNAPbatOT to Nexus') {
          when { expression { isSnapbatot } }
          steps {
              bat "mvn deploy:deploy-file -e -DgroupId=${groupId} -Dversion=${version} -Dpackaging=${packaging} -Durl=${nexusUrl}/repository/${nexusRepoSnapbatot} -Dfile=${filepath} -DartifactId=${artifactId} -DrepositoryId=${mavenRepoId}"

          }
      }
     
     /*
     Ce stage ne se lance que si isSnapbatot est faux
     On pousse la release via le plugin Nexus
     */
      stage('Pubat RELEASE to Nexus') {
          when { expression { !isSnapbatot } }
          steps {
            nexusPublibater nexusInstanceId: 'nexus_localhost', nexusRepositoryId: "${nexusRepoRelease}", packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${filepath}"]], mavenCoordinate: [artifactId: "${artifactId}", groupId: "${groupId}", packaging: "${packaging}", version: "${version}"]]]
          }
      }
   }
    post {
            always {
                junit '**/surefire-reports/*.xml'
                recordIssues enabledForFailure: true, tools: [mavenConsole(),java(),javaDoc()]
                recordIssues enabledForFailure: true, tool: checkStyle()
                recordIssues enabledForFailure: true, tool: spotBugs()
                recordIssues enabledForFailure: true, tool: cpd(pattern:'**/target/cpd.xml')
                recordIssues enabledForFailure: true, tool: pmdParser(pattern:'**/target/pmd.xml')
            }
        }
}
