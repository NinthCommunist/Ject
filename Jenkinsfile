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
                                docker.image('aerokube/selenoid:1.10.4')
                                docker.image('test')
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