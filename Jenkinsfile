pipeline {
    agent any
    parameters {
        choice(name: 'typeTest', choices: ['rest', 'ui'], description: 'REST or UI')

        choice(name: 'xml', choices: ['allRest', 'allUI', 'mainUI', 'randUsRest'], description: 'Which XML')
    }
     stages {
            stage('Build image') {
                steps {
                        script {
                      	      docker.build("test","-f Dockerfile .")
                          }

                }
            }
             stage('Pull browser') {
                    steps {
                          script {
                  	      docker.image('selenoid/chrome:99.0')
                  	      }
                    }
             }
             stage('Run tests') {
                     steps {
                           script {
                               sh "docker pull aerokube/selenoid:1.10.4"
                               sh "docker run -d -v /var/run/docker.sock:/var/run/docker.sock -p 4444:4444 aerokube/selenoid:1.10.4"
                               sh "docker run -d -v /var/run/docker.sock:/var/run/docker.sock -p 4444:4444 test"
                               sh "mvn clean test -DtestType=${params.typeTest} -Dxml=${params.xml}"
                                }
                   	    }

             }
             stage('Reports') {
                     steps {
                        allure([
                   	   includeProperties: false,
                   	   jdk: '',
                   	   properties: [],
                   	   reportBuildPolicy: 'ALWAYS',
                   	   results: [[path: 'target/report']]
                 	   ])
               	        }

             }
      }
}