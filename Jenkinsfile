pipeline {
    agent any
    
    tools {
        maven 'apache-maven-3.9.9'  // Chỉ định Maven phiên bản đã cài đặt trong Jenkins
        jdk 'jdk-21'         // Chỉ định JDK phiên bản bạn đang sử dụng

    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/congviec2406/sele2.git'
            }
        }
        
        stage('Build & Run Tests') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                publishTestNGResults testResultsPattern: '**/target/surefire-reports/testng-results.xml'
            }
        }
    }
}
