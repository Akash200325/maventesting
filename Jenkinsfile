pipeline {
    agent any
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
                            -Dsonar.projectKey=testingtrail ^
                            -Dsonar.projectName='testingtrail' ^
                            -Dsonar.host.url=http://localhost:9000 ^
                            -Dsonar.token=sqp_e9eea3b2dc9288a7e0945cd855febf93ffabf2e0
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
