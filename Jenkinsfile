pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Java Tests') {
            steps {
                bat '''
                echo "Checking for Java files..."
                dir /s /b *.java
                
                echo "Checking for JUnit JAR file..."
                if not exist "junit-platform-console-standalone-1.7.0.jar" (
                    echo "Downloading JUnit JAR file..."
                    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0.jar' -OutFile 'junit-platform-console-standalone-1.7.0.jar'"
                )
                
                echo "Compiling Java files..."
                javac MathUtils.java
                javac -cp .;junit-platform-console-standalone-1.7.0.jar MathUtilsTest.java
                
                echo "Running Java tests..."
                java -jar junit-platform-console-standalone-1.7.0.jar --class-path . --scan-class-path
                '''
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/TEST-*.xml'
                }
            }
        }
        
        stage('Python Tests') {
            steps {
                bat '''
                echo "Checking for Python files..."
                dir /s /b *.py
                
                echo "Installing pytest if needed..."
                pip install pytest
                
                echo "Running Python tests..."
                python -m pytest test_math_utils.py -v --junitxml=python-test-results.xml
                '''
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'python-test-results.xml'
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