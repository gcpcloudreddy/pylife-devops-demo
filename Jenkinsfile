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

        stage ('K8SManiFest-Checkout') {
                steps {

                    git 'https://github.com/pythonlifedevops/aws-k8s-cicd.git'

                }
            }

        stage ('Kubernetes AutoDeployment') {
  

                steps {

                    dir('manifests')
                     {

                    sh 'ls -l'
                    // kubectl apply -f <fileName>.yml
                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6IlFtejh0WGxIVC1KamYyRXNHWFg4bThPdHVBNmh3WmdjOHBfUmQwaWpnaG8ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNiMzc1MDI2LTM3ZDMtNDg1NS1hOTQyLWMxZDM5ZDJkMWQ5ZCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.N2DISPn8DAHLuTZUySmG8Xs4C9hNiglyMHvqkQKBON2_JZefd-cKNbSIKCtbNKU6MiknEH1xUh0hhC7RZayD9Zn8XdhyOo5wJI-1O_auRAHfWKxKM6Z3cjx7FKHfZUPi0HaeGtRtjdm6h_vQww56_3Li_bwY3P21k8CPVwwQBlHO15S_m_WPVwlfihY3kGWfWgHmyOnBIFmGfb810PIdJ4RKNpdteBRHei57s8h0jMhkazUeRRN3FQhoO3y177SztSmW9y2K-uIrX5mEIFqd-aA9oQ9PeJJ85pTrrCo2oJ_AVjXz9Ymc7fny8faDC4m4k4Uu1BAlv5PlbKPRbLCANQ" apply -f deployment.yml'

                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6IlFtejh0WGxIVC1KamYyRXNHWFg4bThPdHVBNmh3WmdjOHBfUmQwaWpnaG8ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNiMzc1MDI2LTM3ZDMtNDg1NS1hOTQyLWMxZDM5ZDJkMWQ5ZCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.N2DISPn8DAHLuTZUySmG8Xs4C9hNiglyMHvqkQKBON2_JZefd-cKNbSIKCtbNKU6MiknEH1xUh0hhC7RZayD9Zn8XdhyOo5wJI-1O_auRAHfWKxKM6Z3cjx7FKHfZUPi0HaeGtRtjdm6h_vQww56_3Li_bwY3P21k8CPVwwQBlHO15S_m_WPVwlfihY3kGWfWgHmyOnBIFmGfb810PIdJ4RKNpdteBRHei57s8h0jMhkazUeRRN3FQhoO3y177SztSmW9y2K-uIrX5mEIFqd-aA9oQ9PeJJ85pTrrCo2oJ_AVjXz9Ymc7fny8faDC4m4k4Uu1BAlv5PlbKPRbLCANQ" apply -f service.yml'
                    
                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6IlFtejh0WGxIVC1KamYyRXNHWFg4bThPdHVBNmh3WmdjOHBfUmQwaWpnaG8ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNiMzc1MDI2LTM3ZDMtNDg1NS1hOTQyLWMxZDM5ZDJkMWQ5ZCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.N2DISPn8DAHLuTZUySmG8Xs4C9hNiglyMHvqkQKBON2_JZefd-cKNbSIKCtbNKU6MiknEH1xUh0hhC7RZayD9Zn8XdhyOo5wJI-1O_auRAHfWKxKM6Z3cjx7FKHfZUPi0HaeGtRtjdm6h_vQww56_3Li_bwY3P21k8CPVwwQBlHO15S_m_WPVwlfihY3kGWfWgHmyOnBIFmGfb810PIdJ4RKNpdteBRHei57s8h0jMhkazUeRRN3FQhoO3y177SztSmW9y2K-uIrX5mEIFqd-aA9oQ9PeJJ85pTrrCo2oJ_AVjXz9Ymc7fny8faDC4m4k4Uu1BAlv5PlbKPRbLCANQ" rollout restart deployment/java-app-deployment'
                    
                    echo "kubernetes deployment is done"

                    sh 'kubectl get deployments'
                    sh 'sleep 100 ; kubectl get services'
                    
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
