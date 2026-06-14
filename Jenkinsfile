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