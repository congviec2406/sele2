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
        
        stage('Build') {
            steps {
                // Chạy Maven để build dự án
                script {
                    sh 'mvn clean install'
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
		post {
        	always {
            // Cleanup hay thông báo gửi email nếu cần
            	echo 'Kiểm thử đã hoàn tất'
        	}
    	}
    }
}
