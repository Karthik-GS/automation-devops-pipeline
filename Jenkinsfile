pipeline {
    agent any

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
                // Now that the environment is fixed, this clean command will pass cleanly!
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