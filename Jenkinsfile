pipeline {
    agent any

    environment {
        SONARQUBE = 'sonarqube'  // Ensure this matches your SonarQube configuration in Jenkins
        MAVEN_HOME = tool 'Maven_3.8.5'  // Update with the name of the Maven tool in your Jenkins configuration
        SONARQUBE_TOKEN =sqp_6b49eaa8c3122ed499fe9d3044645fdcffdef1d4 // Assuming the token is stored as a Jenkins credential
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Akash200325/maventesting.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Run mvn clean install to clean and build the project
                    bat "${MAVEN_HOME}/bin/mvn clean install -X"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run mvn test to execute the tests
                    bat "${MAVEN_HOME}/bin/mvn test"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis
                    bat """
                    ${MAVEN_HOME}/bin/mvn sonar:sonar \
                    -Dsonar.projectKey=maventesting1 \
                    -Dsonar.projectName='maventesting1' \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.token=${SONARQUBE_TOKEN} \
                    -Dsonar.inclusions="src/main/java/**/*" \
                    -Dsonar.exclusions="src/test/java/**/*"
                    """
                }
            }
        }

        stage('Success') {
            steps {
                echo 'Build, Test and SonarQube Analysis completed successfully!'
            }
        }
    }
}
