pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_TOKEN = 'sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout code from GitHub repository
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Execute Maven commands for build, test, and SonarQube analysis
                    bat """
                        mvn clean verify sonar:sonar ^
                        -Dsonar.projectKey=maventesting ^
                        -Dsonar.projectName='maventesting' ^
                        -Dsonar.host.url=${SONAR_HOST_URL} ^
                        -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Wait for SonarQube Quality Gate
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build, test, and SonarQube analysis were successful!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
    }
}
