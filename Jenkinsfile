pipeline {
    agent any

    environment {
        SONARQUBE = 'sonarqube' // Update with your SonarQube server name
        MAVEN_HOME = tool 'Maventool' // Referencing the name you provided
        SONAR_TOKEN = credentials('sonar-token') // Reference to Jenkins credentials for the token
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }

        stage('Build') {
            steps {
                bat "\"${MAVEN_HOME}/bin/mvn\" clean install -X"  // Build with verbose output for debugging
            }
        }

        stage('Run Tests') {
            steps {
                bat "\"${MAVEN_HOME}/bin/mvn\" test"  // Run the tests
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat """
                mvn sonar:sonar \\
                -Dsonar.projectKey=maventesting1 \\
                -Dsonar.projectName='maventesting1' \\
                -Dsonar.host.url=http://localhost:9000 \\
                -Dsonar.token=${SONAR_TOKEN} \\
                -Dsonar.inclusions="src/main/java/**/*" \\
                -Dsonar.exclusions="src/test/java/**/*"
                """
            }
        }

        stage('Success') {
            steps {
                echo 'Build, Test and SonarQube Analysis completed successfully!'
            }
        }
    }
    post {
        always {
            cleanWs()  // Clean up workspace after build
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed, check logs for details.'
        }
    }
}
