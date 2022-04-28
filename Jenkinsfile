pipeline {
    agent any
    parameters {
        choice(name: 'typeTest', choices: ['rest', 'ui'], description: 'Какой вид тестирования будет')

        choice(name: 'xml', choices: ['allRest', 'allUI', 'mainUI', 'randUsRest'], description: 'Какой xml файл используем')
    }
     stages {
            stage('Build image') {
                steps {
                        script {
                      	      sh "docker build -t test -f Dockerfile"
                      	      sh "echo "
                          }

                }
            }
             stage('Pull browser') {
                    steps {
                          script {
                  	    docker.image('selenoid/chrome:92.0')
                  	      }
                    }
             }
             stage('Run tests') {
                     steps {
                        catchError {
                           script {
                       	     docker.image('aerokube/selenoid:1.10.4').withRun('-p 4444:4444 -v /run/docker.sock:/var/run/docker.sock -v $PWD:/etc/selenoid/',
                         	'-timeout 600s -limit 2') { c ->
                           	docker.image('test').inside("--link ${c.id}:selenoid") {
                                 	sh "mvn clean test -DtestType=${params.typeTest} -Dxml=${params.xml}"
                             	    }
                                }
                     	     }
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