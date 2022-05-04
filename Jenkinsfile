pipeline {
    agent any
    parameters {
        choice(name: 'xml', choices: ['allUI', 'mainUI'], description: 'Which XML')
    }
     stages {
            stage('start Selenoid') {
                steps {
                        powershell 'ls'
                        powershell '$current = $PWD -replace "\", "/" -replace "C", "c"'
                        powershell 'docker pull aerokube/selenoid:latest-release'
                        powershell 'docker pull selenoid/chrome:99.0'
                        echo 'bla1'
                        powershell '$PWD'
                        echo 'bla2'
                        powershell 'docker run -d --name selenoid -p 4444:4444 -conf /etc/selenoid/browsers.json  -v //var/run/docker.sock:/var/run/docker.sock -v :/etc/selenoid/:ro aerokube/selenoid:latest-release'
                        powershell 'curl http://localhost:4444/status'
                        powershell 'ls'
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