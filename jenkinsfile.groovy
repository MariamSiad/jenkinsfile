node('agent1') {
    stage('Checkout') {
        git branch: 'main', url: 'https://github.com/MariamSiad/jenkinsfile.git'
    }

    stage('Build') {
        if (fileExists('pom.xml')) {
            echo "Building project with Maven"
            sh 'mvn clean package'
        } else {
            echo "No Maven project found, skipping build"
        }
    }

    stage('Test') {
        echo "No tests to run"
    }

    stage('Archive') {
        echo "No artifacts to archive"
    }
}
