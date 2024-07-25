pipeline {
    agent {
        node {
            label 'java11'
        }
    
    }

    options {
                timestamps()
                buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '2'))
                timeout(time: 240, unit: 'MINUTES')
               // disableConcurrentBuilds()
                }

        stages {
            stage ('AppCodeCheckout') {
                steps {

                    git 'https://github.com/gcpcloudreddy/pylife-devops-demo.git'

                }
            }
            stage ('CI Build') {

                steps {

                    sh 'mvn clean package'
                   
                     }
    
            }

            stage ('Docker Build && Push && DEPLOY ') {
               

                steps {
                    withCredentials([string(credentialsId: 'DOCKERPWD', variable: 'DOCKER_TOKEN')]) {


                    sh 'docker build . -t msrfamilym/app30:test'
                    sh 'docker login -u msrfamilym -p ${DOCKER_TOKEN}'
                    sh 'docker push msrfamilym/app30:test'
                   // sh 'docker run -p 89:8080 -d msrfamilym/app30:test'
                }

                }
            
        }

            stage('Archive and clean workspace') {
                steps {
                    
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                    cleanWs()
                }

            }
        }  
}
