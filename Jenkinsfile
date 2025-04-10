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
                powershell '''
Write-Host "Checking for Java files..."
Get-ChildItem -Path . -Filter *.java -Recurse | ForEach-Object { $_.FullName }

Write-Host "Checking for JUnit JAR file..."
if (-not (Test-Path "junit-platform-console-standalone-1.7.0.jar")) {
    Write-Host "Downloading JUnit JAR file..."
    Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0.jar' -OutFile 'junit-platform-console-standalone-1.7.0.jar'
}

Write-Host "Compiling Java files..."
javac MathUtils.java
javac -cp .;junit-platform-console-standalone-1.7.0.jar MathUtilsTest.java

Write-Host "Running Java tests..."
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
                powershell '''
Write-Host "Checking for Python files..."
Get-ChildItem -Path . -Filter *.py -Recurse | ForEach-Object { $_.FullName }

Write-Host "Installing pytest if needed..."
pip install pytest

Write-Host "Running Python tests..."
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
