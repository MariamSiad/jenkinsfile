node('agent1') {

    def mvnHome = tool 'maven-352'
    env.PATH = "${mvnHome}/bin:${env.PATH}"

    stage('Checkout') {
        git branch: 'main',
            url: 'https://github.com/MariamSiad/jenkinsfile.git'
    }

    stage('Build') {
        echo "Building the project with Maven"
        sh 'mvn clean package'
    }

    stage('Test') {
        echo "Running tests"
        sh 'mvn test'
    }

    stage('Archive') {
        echo "Archiving JAR files"
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }

    stage('Deploy') {
        echo "Deploying to Apache HTTPD"
        sh 'sudo cp target/*.jar /var/www/html/'
        sh 'sudo systemctl restart httpd'
    }

    stage('Publish Test Results') {
        junit 'target/surefire-reports/*.xml'
    }
}
