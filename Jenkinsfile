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
                        sh "echo before image build"
                      	      docker.build("test","-f Dockerfile .")
                      	      sh "echo image build end"
                          }

                }
            }
             stage('Pull browser') {
                    steps {
                          script {
                  	    docker.image('selenoid/chrome:99.0')
                  	    sh "echo pull browser end"
                  	      }
                    }
             }
             stage('Run tests') {
                     steps {
                        catchError {
                           script {
                           sh "dir"
                       	     docker.image('aerokube/selenoid:1.10.4').withRun('-p 4444:4444 -v /run/docker.sock:/var/run/docker.sock -v :/etc/selenoid/',
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