pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = 'sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a' // Your SonarQube token
        SONARQUBE_URL = 'http://localhost:9000'   // Your SonarQube server URL
        SONARQUBE_PROJECT_KEY = 'maventesting'    // Your SonarQube project key
        SONARQUBE_PROJECT_NAME = 'maventesting'   // Your SonarQube project name
        
        // PMD settings
        PMD_RULESET_PATH = 'rulesets/java/quickstart.xml'  // Path to PMD ruleset file
        PMD_MINIMUM_TOKENS = 100  // Minimum tokens for PMD

        // Checkstyle settings
        CHECKSTYLE_CONFIG_FILE = 'google_checks.xml'  // Path to Checkstyle config file
        
        // CPD (Copy/Paste Detector) settings
        CPD_MINIMUM_TOKENS = 100  // Minimum tokens for code duplication detection
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
        stage('PMD Analysis') {
            steps {
                bat """
                    mvn pmd:check ^ 
                    -Dpmd.ruleset=${PMD_RULESET_PATH} ^ 
                    -Dpmd.minimumTokens=${PMD_MINIMUM_TOKENS}
                """
            }
        }
        stage('Checkstyle Analysis') {
            steps {
                bat """
                    mvn checkstyle:check ^ 
                    -Dcheckstyle.configLocation=${CHECKSTYLE_CONFIG_FILE}
                """
            }
        }
        stage('CPD Analysis') {
            steps {
                bat """
                    mvn pmd:cpd ^ 
                    -Dpmd.cpd.minimumTokens=${CPD_MINIMUM_TOKENS}
                """
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
