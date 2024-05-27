pipeline {
    agent any

    stages {
        stage('Stage 1: Git Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/kalyaniverma/PetGrooming_Application.git'
            }
        }
        // stage('Stage 2: Remove npm proxy') {
        //     steps {
        //         sh 'npm config rm proxy'
        //         sh 'npm config rm http-proxy'
        //         sh 'npm config rm https-proxy'
        //     }
        // }
        stage('Stage 2: frontend Build,push to Dockerhub ') {
            steps {
                dir('frontend'){
                sh "npm install"
                sh 'docker build -f Dockerfile.dev -t frontend-image .'

                script{
                   docker.withRegistry('', 'DockerHubCred') {
                                  sh "docker tag frontend-image kalyaniv2001/expense_tracker_react:latest"
                                  sh "docker push kalyaniv2001/expense_tracker_react:latest"}
                 }
                //sh 'docker rmi -f frontend-image'

            }
            }
        }
        stage("Stage 3: backend Build,push to dockerhub ") {
            steps {
                dir('backend'){
                sh "mvn clean package"
                sh 'docker build -t backend-image .'

                script{
                   docker.withRegistry('', 'DockerHubCred') {
                                  sh "docker tag backend-image kalyaniv2001/expense_tracker_springboot:latest"
                                  sh "docker push kalyaniv2001/expense_tracker_springboot:latest"}
                 }
                 //sh ' docker rmi -f backend-image'

            }}
        }

        stage("stage 4: remove images "){
        //to avoid naming conflict with next build
           steps{
            sh 'docker rmi -f backend-image'
            sh 'docker rmi -f frontend-image'
           }
        }

	stage('Run Ansible Playbook') {
            steps {		
                script {
		  dir(''){
                    sh '''echo '1820' | sudo -S ansible-playbook -i inventory playbook.yml'''
                  }
		}
            }
        }	
    }
}


