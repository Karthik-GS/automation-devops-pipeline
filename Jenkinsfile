pipeline {
    agent any

    // This tells Jenkins to automatically load and inject 'M3' into the system PATH
    tools {
        maven 'M3'
    }

    stages {
        stage('Pull Cloud Repository') {
            steps {
                checkout scm
            }
        }

        stage('Execute UI, API & DB Test Suite') {
            steps {
                // Install real Google Chrome and dependencies inside the container
                sh '''
            echo ">>> Installing Google Chrome Browser & Dependencies..."
            apt-get update && apt-get install -y wget gnupg
            wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
            sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
            apt-get update
            apt-get install -y google-chrome-stable
            
            echo ">>> Browser Installation Complete. Verifying Version:"
            google-chrome --version
        '''
                // Because of the tools block above, you can now call 'mvn' directly cleanly
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