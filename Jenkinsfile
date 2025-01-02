pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = 'sqp_c2f48f5ea3d3443b53e48a67a3d591b20d7f8d9a'
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_PROJECT_KEY = 'maventesting'
        SONARQUBE_PROJECT_NAME = 'maventesting'
        
        // PMD settings
        PMD_RULESET_PATH = 'rulesets/custom-ruleset.xml'
        PMD_MINIMUM_TOKENS = 100

        // Checkstyle settings (updated to reflect new location)
        CHECKSTYLE_CONFIG_FILE = 'config/checkstyle/google_checks.xml'

        // CPD settings
        CPD_MINIMUM_TOKENS = 100
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    bat 'mvn clean install -X'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    script {
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
        }

        stage('PMD Analysis') {
            steps {
                script {
                    bat """
                        mvn pmd:check ^
                        -Dpmd.ruleset=${PMD_RULESET_PATH} ^
                        -Dpmd.minimumTokens=${PMD_MINIMUM_TOKENS}
                    """
                }
            }
        }

        stage('Checkstyle Analysis') {
            steps {
                script {
                    bat """
                        mvn checkstyle:check ^
                        -Dcheckstyle.configLocation=${CHECKSTYLE_CONFIG_FILE}
                    """
                }
            }
        }

        stage('CPD Analysis') {
            steps {
                script {
                    bat """
                        mvn pmd:cpd ^
                        -Dpmd.cpd.minimumTokens=${CPD_MINIMUM_TOKENS}
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
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            junit '**/target/test-*.xml'
        }
    }
}
