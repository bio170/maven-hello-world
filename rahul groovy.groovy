

pipeline {
// Jenkinsfile (Scripted Pipeline)
node { // node/agent
  stage('Stage 1') {
    echo 'Hello World' // echo Hello World
  }
}

triggers {
    cron('H */2 * * 1-3')
  }


  stages {
    stage("Build") {
      steps {
        sh 'mvn -v'
      }
    }

    stage("Testing") {
      parallel {
        stage("Unit Tests") {
          agent { docker 'openjdk:7-jdk-alpine' }
          steps {
            sh 'java -version'
          }
        }
        stage("Functional Tests") {
          agent { docker 'openjdk:8-jdk-alpine' }
          steps {
            sh 'java -version'
          }
        }
        stage("Integration Tests") {
          steps {
            sh 'java -version'
          }
        }
      }
    }

    stage("Deploy") {
      steps {
        echo "Deploy!"
      }
    }
  }
}

