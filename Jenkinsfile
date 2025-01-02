pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = 'sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a'
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_PROJECT_KEY = 'maventesting'
        SONARQUBE_PROJECT_NAME = 'maventesting'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install -X'
            }
        }
        stage('PMD Analysis') {
            steps {
                script {
                    // Run PMD analysis as a post-build action
                    pmd analysisPattern: '**/target/pmd.xml', ruleSet: 'rulesets/java/quickstart.xml'
                }
            }
        }
        stage('Generate Checksum') {
            steps {
                script {
                    // Generate checksum for the artifact
                    checksum file: 'target/your-artifact.jar', algorithm: 'SHA-256'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
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
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
        }
    }
}
