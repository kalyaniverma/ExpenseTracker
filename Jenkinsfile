pipeline {
    agent any

    stages {
        stage('1. Git Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/kalyaniverma/PetGrooming_Application.git'
            }
        }

        stage('2. Testing backend code'){
            steps{
                dir('backend'){
                    sh 'mvn test'
                }
            }

            post{ //Used to generate report in visulaization form telling how many test cases passes or fail
                always{
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        
        stage('3. Frontend: Build and Push docker image') {
            steps {
                dir('frontend'){
                sh "npm install"
                sh 'docker build -f Dockerfile.dev -t frontend-image .'

                script{
                   docker.withRegistry('', 'DockerHubCred') {
                                  sh "docker tag frontend-image kalyaniv2001/expense_tracker_react:latest"
                                  sh "docker push kalyaniv2001/expense_tracker_react:latest"}
                 }

            }
            }
        }
        stage('4. Backend: Build and Push docker image') {
            steps {
                dir('backend'){
                sh "mvn clean package"
                sh 'docker build -t backend-image .'

                script{
                   docker.withRegistry('', 'DockerHubCred') {
                                  sh "docker tag backend-image kalyaniv2001/expense_tracker_springboot:latest"
                                  sh "docker push kalyaniv2001/expense_tracker_springboot:latest"}
                 }

            }}
        }

        stage('5. Clean Docker Images'){
        //to avoid naming conflict with next build
           steps{
            sh 'docker container prune -f'
            sh 'docker image prune -f'
           }
        }

	stage('6. Run Ansible Playbook') {
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


