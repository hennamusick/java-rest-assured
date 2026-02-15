pipeline {
    agent any
    
    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:${env.PATH}"
        JAVA_HOME = "/opt/homebrew/opt/openjdk"
    }
    
    options {
        timeout(time: 1, unit: 'HOURS')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }
    
    stages {
        stage('Setup') {
            steps {
                script {
                    echo '========================================='
                    echo 'Java REST Assured Test Suite'
                    echo '========================================='
                    sh 'java -version'
                    sh 'mvn -version'
                }
            }
        }
        
        stage('Clean') {
            steps {
                echo 'Cleaning previous build artifacts...'
                sh 'mvn clean'
            }
        }
        
        stage('Compile') {
            steps {
                echo 'Compiling project...'
                sh 'mvn compile'
            }
        }
        
        stage('Run Regression Tests') {
            steps {
                echo 'Running regression test suite...'
                sh 'mvn -DsuiteXmlFile=regression-testing.xml test || true'
            }
        }
        
        stage('Publish Results') {
            steps {
                echo 'Publishing test results...'
                junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                
                // Allure report (if available)
                script {
                    if (fileExists('allure-results')) {
                        echo 'Allure report artifacts found'
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo '========================================='
            echo 'Test Run Complete'
            echo '========================================='
            cleanWs(deleteDirs: true, patterns: [
                [pattern: 'target/surefire-reports', type: 'INCLUDE']
            ])
        }
        
        success {
            echo '✓ Pipeline completed successfully'
            // You can add email notifications here
        }
        
        failure {
            echo '✗ Pipeline failed - check logs above'
            // You can add email notifications here
        }
    }
}
