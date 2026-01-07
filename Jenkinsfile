pipeline {
    agent any

    environment {
        AWS_REGION = 'eu-north-1'
        AWS_ACCOUNT_ID = '902488664535'
        ECR_REPO_NAME = 'cart-service'
        IMAGE_TAG = "${env.BUILD_NUMBER}"

        ECR_REGISTRY = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
        IMAGE_URI = "${ECR_REGISTRY}/${ECR_REPO_NAME}:${IMAGE_TAG}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                  ./gradlew clean build
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                  docker build -t ${ECR_REPO_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        stage('Login to AWS ECR') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding',
                                  credentialsId: 'aws-credentials']]) {
                    sh '''
                      aws ecr get-login-password --region ${AWS_REGION} \
                      | docker login \
                      --username AWS \
                      --password-stdin ${ECR_REGISTRY}
                    '''
                }
            }
        }

        stage('Tag & Push Image') {
            steps {
                sh '''
                  docker tag ${ECR_REPO_NAME}:${IMAGE_TAG} ${IMAGE_URI}
                  docker push ${IMAGE_URI}
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Docker image pushed successfully: ${IMAGE_URI}"
        }
        failure {
            echo "❌ Build or push failed"
        }
        cleanup {
            sh '''
              docker rmi ${IMAGE_URI} || true
              docker rmi ${ECR_REPO_NAME}:${IMAGE_TAG} || true
            '''
        }
    }
}
