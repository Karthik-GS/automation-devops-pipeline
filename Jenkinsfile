pipeline {
    agent any

    stages {
        stage('Pull Cloud Repository') {
            steps {
                checkout scm
            }
        }

        stage('Execute UI, API & DB Test Suite') {
            steps {
                echo ">>> Spinning up a Chrome-stabilized container via explicit shell execution..."

                // This downloads a container that already has Maven 3.9, JDK 17, and Chrome installed.
                // We mount your current workspace directory into the container so it can access your project files.
                sh '''
                    docker run --rm \
                    -v /var/jenkins_home/workspace/my-first-utomation-pipeline:/app \
                    -w /app \
                    -e JENKINS_URL=true \
                    markhobson/maven-chrome:3.9.6-jdk-17 \
                    mvn clean test -Dheadless=true
                '''
            }
        }

        stage('Generate Execution Analytics') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}