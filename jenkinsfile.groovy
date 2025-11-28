node ('agent1'){
    def maven = tool 'maven-352', type:'maven'
    stage('Checkout') {
        git branch: 'main',
            url: 'https://github.com/Hassan-Eid-Hassan/cicd-lab2.git'
    
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

