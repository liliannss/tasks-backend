pipeline {
    agent any
    stages {
        stage ('Buil Back-End') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
    }
}