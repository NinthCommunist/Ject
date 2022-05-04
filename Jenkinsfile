pipeline {
    agent any
    parameters {
        choice(name: 'xml', choices: ['allUI', 'mainUI'], description: 'Which XML')
    }
     stages {
            stage('start Selenoid') {
                steps {
                        powershell 'docker pull aerokube/selenoid:latest-release'
                        powershell 'docker pull selenoid/chrome:99.0' //в мануале указано, что нужно сделать пул, но и без этого работает(после удаление образа)
                        powershell 'docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${PWD}\\src\\test\\resources\\selenoid:/etc/selenoid/:ro aerokube/selenoid:latest-release'
                        bat 'curl http://localhost:4444/status'
                         }
            }
            stage('test') {
                        steps {
                                bat "mvn clean test -DtestType=ui -Dxml=${params.xml} -Dselenoid=true"
                        }
            }
     }
      post {
             always {
                 script {
                     powershell 'docker stop selenoid'
                     powershell 'docker rm selenoid'
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