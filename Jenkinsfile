pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = 'sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a' // Your SonarQube token
        SONARQUBE_URL = 'http://localhost:9000'   // Your SonarQube server URL
        SONARQUBE_PROJECT_KEY = 'maventesting'    // Your SonarQube project key
        SONARQUBE_PROJECT_NAME = 'maventesting'   // Your SonarQube project name
    }
    stages {
        stage('Build') {
    steps {
        bat 'mvn clean install -X'
    }
}
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') { // Ensure 'sonarqube' is correctly configured in Jenkins
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=${SONARQUBE_PROJECT_KEY} ^
                        -Dsonar.projectName=${SONARQUBE_PROJECT_NAME} ^
                        -Dsonar.host.url=${SONARQUBE_URL} ^
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    """
                }
            }
        }
        stage('Quality Gate') {
            steps {
                script {
                    def qg = waitForQualityGate() // Check SonarQube Quality Gate status
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
        }
    }
}
