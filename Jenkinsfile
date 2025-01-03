pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = credentials('sonar-token')  // Use Jenkins credentials securely
        SONARQUBE_URL = 'http://localhost:9000'           // Your SonarQube server URL
        SONARQUBE_PROJECT_KEY = 'maventesting'            // Your SonarQube project key
        SONARQUBE_PROJECT_NAME = 'maventesting'           // Your SonarQube project name
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean install -X'  // Use 'sh' instead of 'bat' for cross-platform compatibility
            }
        }
        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube analysis...'
                withSonarQubeEnv('sonarqube') {  // Ensure 'sonarqube' is configured in Jenkins
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${SONARQUBE_PROJECT_KEY} \
                        -Dsonar.projectName=${SONARQUBE_PROJECT_NAME} \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    """
                }
            }
        }
        stage('Quality Gate') {
            steps {
                echo 'Checking SonarQube Quality Gate...'
                script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
        success {
            echo 'Pipeline succeeded!'
        }
    }
}
