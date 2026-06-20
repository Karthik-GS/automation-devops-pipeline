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
                sh '''
                    echo ">>> Handling package directories natively..."
                    # Instead of a global apt update that requires root, we fix the specific directory permission if we can,
                    # or download the standalone Chrome binary directly to our workspace where we have 100% control!
                    
                    echo ">>> Fetching standalone Chrome Headless binary into workspace..."
                    mkdir -p bower_components/chrome
                    cd bower_components/chrome
                    
                    # Downloading a portable, root-less version of Chromium/Chrome
                    wget -q https://storage.googleapis.com/chrome-for-testing-public/122.0.6261.94/linux64/chrome-linux64.zip
                    unzip -q chrome-linux64.zip
                    
                    echo ">>> Chrome Binary downloaded. Setting path visibility..."
                    cd ../..
                    export CHROME_PATH=$(pwd)/bower_components/chrome/chrome-linux64/chrome
                    chmod +x $CHROME_PATH
                    
                    echo ">>> Verifying Chrome execution version without root installations:"
                    $CHROME_PATH --version
                '''

                // Execute Maven tests cleanly using your global M3 installation
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