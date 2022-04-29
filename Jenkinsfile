pipeline {
    agent any
    parameters {
        choice(name: 'xml', choices: ['allUI', 'mainUI'], description: 'Which XML')
    }
     stages {
            stage('start Selenoid') {
                steps {
                        bat 'docker pull selenoid/chrome:99.0'
                        bat 'cd src/test/resources/selenoid'
                		bat "src/test/resources/selenoid_manager/cm_win64.exe selenoid start --browsers 'chrome:99.0'"
                		bat 'src/test/resources/selenoid_manager/cm_win64.exe selenoid status'
                		bat 'curl http://localhost:4444/status'
                         }
            }
            stage('test') {
                        steps {
            		        bat 'mvn clean test -DtestType=ui -Dxml=${params.xml}'
                        }
            }
     }
      post {
             always {
                 script {
                     sh 'docker stop selenoid'
                     sh 'docker rm selenoid'
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