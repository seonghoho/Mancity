pipeline {
    agent any
    tools {
        gradle 'Gradle 8.5'
    }
    environment {
        DOCKER_IMAGE_NAME = 'joonseong/mancity-calc'
        DOCKERFILE_PATH = './calc/Dockerfile'
        CONTAINER_NAME = 'mancity-calc'
        REGISTRY_CREDENTIAL = 'dockerhub_IdPwd'
        DOCKER_IMAGE = ''
        DOCKER_IMAGE_TAG = 'latest'
    }
    stages {
        stage('GitLab Clone') {
            steps {
                git branch : 'develop-be-calc', credentialsId: 'gitlab_access_token', url: 'https://lab.ssafy.com/s10-ai-image-sub2/S10P22C201.git'
            }
        }
        stage('Gradle Build') {
            steps {
                echo 'Building..'
                dir('./calc') {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean bootjar'
                }
            }
        }
        stage('Docker Build Image') {
            steps {
                dir('./calc') {
                    script {
                        DOCKER_IMAGE = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", "-f Dockerfile .")
                    }
                }
            }
        }
        stage('Push Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('', REGISTRY_CREDENTIAL) {
                        DOCKER_IMAGE.push()
                    }
                }
            }
        }
        stage('Docker Clean Image') {
            steps {
                dir('./calc') {
                    sh 'docker rmi $DOCKER_IMAGE_NAME'
                }
            }
        }
        stage('Pull from DockerHub') {
            steps {
                script {
                    sh 'docker pull ${DOCKER_IMAGE_NAME}'
                }
            }
        }
        stage('Delete Previous back Docker Container'){
            steps {
                script {
                    def containerInfo = sh(script: "docker inspect ${CONTAINER_NAME}", returnStatus: true)
                    if (containerInfo == 0) {
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    } else {
                        echo "CALC container does not exist. Skipping deletion."
                    }
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    sh 'docker run -d --name ${CONTAINER_NAME} -p 8083:8083 ${DOCKER_IMAGE_NAME}'
                }
            }
        }
    }
}
