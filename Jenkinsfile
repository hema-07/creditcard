pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                withMaven(maven: 'Maven_3.6.0') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Build') {
            steps {
                withMaven(maven: 'Maven_3.6.0') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Test') {
            steps {
                withMaven(maven: 'Maven_3.6.0') {
                    sh 'mvn test'
                }
            }
        }
        stage('SonarQube analysis') {
            withSonarQubeEnv(credentialsId: 'f225455e-ea59-40fa-8af7-08176e86507a', installationName: 'My SonarQube Server') {
              sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }
          }
        }
        stage('Verify') {
            steps {
                withMaven(maven: 'Maven_3.6.0') {
                    sh 'mvn verify'
                }
            }
        }
    }
}