pipeline {
    agent any
    environment {
        SONARQUBE = 'sonarqube' // Update with your SonarQube server name
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the Git repository
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }
        stage('Build') {
            steps {
                // Run Maven clean install to build the project
                bat 'mvn -X clean install'
            }
        }
        stage('static code analysis'){
            steps {
                bat 'mvn pmd:pmd'
            }
        }
        stage('Run Tests') {
            steps {
                // Run Maven tests
                bat 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                bat ''' 
                    mvn sonar:sonar ^
                        -Dsonar.projectKey=maventesting1 ^
                        -Dsonar.projectName='maventesting1' ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.token=sqp_6b49eaa8c3122ed499fe9d3044645fdcffdef1d4 ^
                        -Dsonar.inclusions="src/main/java/**/*" ^
                        -Dsonar.exclusions="src/test/java/**/*"
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
