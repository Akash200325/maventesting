pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_TOKEN = 'sqp_4aab5b61ea6a596d05a7400f52859fd8009c9876'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout code from your GitHub repository
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }

        stage('Build and Test') {
            steps {
                // Run Maven clean and verify commands
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('sonarqube') { // 'SonarQube' should match your Jenkins SonarQube configuration name
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=maventesting \
                        -Dsonar.projectName="maventesting" \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.login=${SONAR_TOKEN}
                    '''
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
            echo 'Build and SonarQube analysis successful!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
    }
}
