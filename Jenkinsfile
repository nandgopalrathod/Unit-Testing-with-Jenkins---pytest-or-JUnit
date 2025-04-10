pipeline {
    agent any
    
    stages {
        // Single checkout stage
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/nandgopalrathod/Unit-Testing-with-Jenkins---pytest-or-JUnit.git'
                    ]]
                ])
            }
        }
        
        stage('Setup Environment') {
            steps {
                bat 'set'
                bat 'java -version'
                bat 'python --version'
                bat 'pytest --version'
            }
        }
        
        stage('Java Tests') {
            steps {
                bat '''
                echo "Compiling Java files..."
                javac MathUtils.java
                javac -cp .;junit-platform-console-standalone-1.7.0.jar MathUtilsTest.java
                echo "Running Java tests..."
                java -jar junit-platform-console-standalone-1.7.0.jar --class-path . --scan-class-path --reports-dir=test-results
                '''
            }
            post {
                always {
                    junit 'test-results/**/*.xml'
                }
            }
        }
        
        stage('Python Tests') {
            steps {
                bat '''
                echo "Running Python tests..."
                python -m pytest test_math_utils.py -v --junitxml=python-test-results.xml
                '''
            }
            post {
                always {
                    junit 'python-test-results.xml'
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
            archiveArtifacts artifacts: '**/test-results/**/*.xml', allowEmptyArchive: true
        }
    }
}
