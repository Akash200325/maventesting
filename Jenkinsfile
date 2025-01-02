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
                withSonarQubeEnv('sonarqube') { // Replace 'SonarQube' with the name of your SonarQube server configuration
                    bat """
                        mvn sonar:sonar ^ 
                        -Dsonar.projectKey=maventesting ^ 
                        -Dsonar.projectName="maventesting" ^ 
                        -Dsonar.host.url=http://localhost:9000 ^ 
                        -Dsonar.token=sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a
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
