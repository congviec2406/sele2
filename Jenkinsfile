pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'  // Chỉ định Maven phiên bản đã cài đặt trong Jenkins
        jdk 'JDK 21'         // Chỉ định JDK phiên bản bạn đang sử dụng
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone mã nguồn từ Git repository
                git 'https://github.com/congviec2406/sele2.git'
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

        stage('Test') {
            steps {
                // Chạy kiểm thử tự động bằng Maven
                script {
                    sh 'mvn test'
                }
            }
        }

    }
 	stage('Publish Test Results') {
            steps {
                publishTestNGResults testResultsPattern: '**/target/surefire-reports/testng-results.xml'
            }
        }

    post {
        always {
            // Cleanup hay thông báo gửi email nếu cần
            echo 'Kiểm thử đã hoàn tất'
        }
    }
}
