pipeline {
    agent any
    parameters {
        choice(name: 'xml', choices: ['allUI', 'mainUI'], description: 'Which XML')
    }
     stages {
            stage('start Selenoid') {
                steps {
                        powershell 'ls'
                		powershell 'curl http://localhost:4444/status'
                         }
            }
            stage('test') {
                        steps {
                            echo'${params.xml}'
            		        bat 'mvn clean test -DtestType=ui -Dxml=allUI'
                        }
            }
     }
      post {
             always {
                 script {
                     bat 'docker stop selenoid'
                     bat 'docker rm selenoid'
                     allure([
                         includeProperties: false,
                         jdk: '',
                         properties: [],
                         reportBuildPolicy: 'ALWAYS',
                         results: [[path: 'target/allure-results']]
                         ])
                 }
             }
         }

}