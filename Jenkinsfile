pipeline {
    agent any

    environment {
        SONARQUBE = 'sonarqube' // Update with your SonarQube server name
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }

        stage('Build') {
            steps {
                // Run the Maven build
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                // Run the Maven tests
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run the SonarQube analysis
                bat '''
                mvn clean verify sonar:sonar 
                                 -Dsonar.projectKey=maventesting1 ^
                                 -Dsonar.projectName='maventesting1' ^
                                 -Dsonar.host.url=http://localhost:9000 ^
                                 -Dsonar.token=sqp_6b49eaa8c3122ed499fe9d3044645fdcffdef1d4
                '''
            }
        }

        stage('Success') {
            steps {
                // Print success message
                echo 'Build, Test, and SonarQube Analysis completed successfully!'
            }
        }
    }
}
