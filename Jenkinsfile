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
                // Simply call maven directly; the Java code takes care of the infrastructure setup safely
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