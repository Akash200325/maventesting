pipeline {
    agent any
    environment {
        SONARQUBE = 'sonarqube' // Update with your SonarQube server name
        MAVEN_HOME = tool 'Maventool' // Referencing the name you provided
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }
        stage('Build') {
            steps {
                bat "'${MAVEN_HOME}/bin/mvn' clean install -X"
            }
        }
        stage('Run Tests') {
            steps {
                bat "'${MAVEN_HOME}/bin/mvn' test"
            }
        }
        stage('SonarQube Analysis') {
            steps {
                bat '''
                mvn sonar:sonar -Dsonar.projectKey=maventesting1 -Dsonar.projectName='maventesting1' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_6b49eaa8c3122ed499fe9d3044645fdcffdef1d4 -Dsonar.inclusions="src/main/java/**/*" -Dsonar.exclusions="src/test/java/**/*"
                '''
            }
        }
        stage('Success') {
            steps {
                echo 'Build, Test and SonarQube Analysis completed successfully!'
            }
        }
    }
}
