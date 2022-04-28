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
                  	      echo "blablalba3"
                  	      }
                    }
             }
             stage('Run tests') {
                     steps {
                           script {
                           echo "blablalba4"
                       	     docker.image('aerokube/selenoid:1.10.4 -p 4444:4444' )
                       	     echo "blablalba56"
                           	docker.image('test'){
                           	echo "blablalba5"
                                 	sh "mvn clean test -DtestType=${params.typeTest} -Dxml=${params.xml}"
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