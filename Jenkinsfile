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
                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6InFjNjJBY2U1X2taWklza0JHdVVscHRSc3Y4NnVEQmRHYUROejlUSzhQZk0ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjJiYTNhYmM2LTE4ODAtNDBjMi05ZTdkLTg2Y2QyOTRkZGNmMSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.mMbnFdf1vZR_LHSl8dkbewmLODXFxjvOmcA276nJkg9RzRt3DSAHvfRFoLTLv-gRYpgq866D4SNFcMC2CYKljL7-6DB7F_OVRaPoXhd6w8nqGXVXJrURXuqBkoPzyU856oQuCKLKVbWl3oA93wyBRqLNlorPOz311aU3dIzBmTTaf6b7VlX-ZsmVjDOa7BPE7_1agVpXTR2ZPJ1T4gMnGP1-v6TN9FvCRDj-zTlJQzl4G5XLm8wlKWgxDtFfNFwtPK7xSF-F-kxaogZLKDeXV2-rfLWxQw19FHxhYn-SQE3bDJ6FlattECFyBRGiJmyTgxn4uepCx306apoc3HN0Pw" apply -f deployment.yml'

                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6InFjNjJBY2U1X2taWklza0JHdVVscHRSc3Y4NnVEQmRHYUROejlUSzhQZk0ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjJiYTNhYmM2LTE4ODAtNDBjMi05ZTdkLTg2Y2QyOTRkZGNmMSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.mMbnFdf1vZR_LHSl8dkbewmLODXFxjvOmcA276nJkg9RzRt3DSAHvfRFoLTLv-gRYpgq866D4SNFcMC2CYKljL7-6DB7F_OVRaPoXhd6w8nqGXVXJrURXuqBkoPzyU856oQuCKLKVbWl3oA93wyBRqLNlorPOz311aU3dIzBmTTaf6b7VlX-ZsmVjDOa7BPE7_1agVpXTR2ZPJ1T4gMnGP1-v6TN9FvCRDj-zTlJQzl4G5XLm8wlKWgxDtFfNFwtPK7xSF-F-kxaogZLKDeXV2-rfLWxQw19FHxhYn-SQE3bDJ6FlattECFyBRGiJmyTgxn4uepCx306apoc3HN0Pw" apply -f service.yml'
                    
                    sh 'kubectl --server=https://47FE10C4131DBE8C471F9E5225864348.gr7.us-east-1.eks.amazonaws.com --insecure-skip-tls-verify --token="eyJhbGciOiJSUzI1NiIsImtpZCI6InFjNjJBY2U1X2taWklza0JHdVVscHRSc3Y4NnVEQmRHYUROejlUSzhQZk0ifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjJiYTNhYmM2LTE4ODAtNDBjMi05ZTdkLTg2Y2QyOTRkZGNmMSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.mMbnFdf1vZR_LHSl8dkbewmLODXFxjvOmcA276nJkg9RzRt3DSAHvfRFoLTLv-gRYpgq866D4SNFcMC2CYKljL7-6DB7F_OVRaPoXhd6w8nqGXVXJrURXuqBkoPzyU856oQuCKLKVbWl3oA93wyBRqLNlorPOz311aU3dIzBmTTaf6b7VlX-ZsmVjDOa7BPE7_1agVpXTR2ZPJ1T4gMnGP1-v6TN9FvCRDj-zTlJQzl4G5XLm8wlKWgxDtFfNFwtPK7xSF-F-kxaogZLKDeXV2-rfLWxQw19FHxhYn-SQE3bDJ6FlattECFyBRGiJmyTgxn4uepCx306apoc3HN0Pw" rollout restart deployment/java-app-deployment'
                    
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
