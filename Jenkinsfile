pipeline {
    agent any

    stages {
        stage('Pull Cloud Repository') {
            steps {
                checkout scm
            }
        }

        stage('Execute UI, API & DB Test Suite') {
            agent {
                docker {
                    // Running this stage inside a clean container that has Maven 3.9 and Java 17 pre-installed
                    image 'maven:3.9.6-eclipse-temurin-17'
                    // Forces the container to run as root so apt-get can install Chrome smoothly
                    args '-u root'
                }
            }
            steps {
                sh '''
                    echo ">>> Preparing package manager directories..."
                    rm -rf /var/lib/apt/lists/*
                    mkdir -p /var/lib/apt/lists/partial

                    echo ">>> Installing Google Chrome Browser & Core Dependencies..."
                    apt-get update && apt-get install -y wget gnupg curl
                    
                    # Modern and secure way to add the Google Chrome repository key
                    mkdir -p /etc/apt/keyrings
                    curl -fsSL https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /etc/apt/keyrings/google-chrome.gpg
                    
                    echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list
                    
                    apt-get update
                    apt-get install -y google-chrome-stable
                    
                    echo ">>> Browser Installation Complete. Verifying Version:"
                    google-chrome --version
                '''

                // Runs Maven test inside the container. We no longer need the global 'tools' configuration block
                sh 'mvn clean test -Dheadless=true'
            }
        }

        stage('Generate Execution Analytics') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}