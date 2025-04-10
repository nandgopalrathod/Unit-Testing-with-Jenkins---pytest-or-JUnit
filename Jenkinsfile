pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'YOUR_GITHUB_REPO_URL'
            }
        }
        
        stage('Java Tests') {
            steps {
                sh '''
                javac MathUtils.java
                javac -cp .:junit-platform-console-standalone-1.7.0.jar MathUtilsTest.java
                java -jar junit-platform-console-standalone-1.7.0.jar --class-path . --scan-class-path
                '''
            }
            post {
                always {
                    junit '**/TEST-*.xml'
                }
            }
        }
        
        stage('Python Tests') {
            steps {
                sh 'python -m pytest test_math_utils.py -v --junitxml=python-test-results.xml'
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
        }
    }
}