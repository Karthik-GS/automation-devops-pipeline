pipeline {
    agent any

     environment {
         // Keeps variables out of the execution blocks
         MAVEN_HOME = tool 'M3'
     }

    stages {
        stage('Pull Cloud Repository') {
            steps {
                checkout scm // Dynamically pulls down fresh code from GitHub Cloud
            }
        }

        stage('Execute UI, API & DB Test Suite') {
            steps {
                // Runs all 30+ tests headlessly inside Jenkins
                sh 'mvn clean test -Dheadless=true'
            }
        }

        stage('Generate Execution Analytics') {
            steps {
                // Parses target/allure-results to create your interactive dashboard
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}