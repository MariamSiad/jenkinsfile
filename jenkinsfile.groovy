node ('agent1'){
    def maven = tool 'maven-352', type:'maven'
    stage('Checkout') {
        git branch: 'main',
            url: 'https://github.com/MariamSiad/jenkinsfile.git'
    
    }
    stage('Build') {
        sh "mvn clean package"
    }
    stage('Test') {
        sh "mvn test"
    }
    stage('Archive') {
        archiveArtifacts artifacts: 'target/*.jar'
    }
}

