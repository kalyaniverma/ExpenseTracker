pipeline {
    agent any

    stages {
        stage('Stage 1: Git Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/kalyaniverma/PetGrooming_Application.git'
            }
        }
        stage('Stage 2: Remove npm proxy') {
            steps {
                sh 'npm config rm proxy'
                sh 'npm config rm http-proxy'
                sh 'npm config rm https-proxy'
            }
        }
        stage('Stage 3: frontend Build') {
            steps {
                dir('frontend'){
                sh "npm install"
                sh 'docker build -f Dockerfile.dev -t frontend-image .'
            }
            }
        }
        stage("Stage 3: backend Build") {
            steps {
                dir('backend'){
                sh "npm install"
                sh 'docker build -t backend-image .'
            }}
        }
    }
}