pipeline {
    agent any
    parameters {
        choice(name: 'xml', choices: ['allUI', 'mainUI'], description: 'Which XML')
    }
     stages {
            stage('start Selenoid') {
                steps {
                        echo "echo1"
                        sh 'docker pull selenoid/chrome'
                		sh "src/test/resources/selenoid/cm_win64 selenoid start --browsers 'chrome:99.0'"
                		sh 'src/test/resources/selenoid/cm_win64 selenoid status'
                		sh 'curl http://localhost:4444/status'
                         }
            }
            stage('test') {
                        steps {
            		        sh 'mvn clean test -DtestType=ui -Dxml=${params.xml}'
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