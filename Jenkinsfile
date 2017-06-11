pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                sh "./gradlew -Dgradle.user.home=$HOME/.gradle clean build"
                archiveUnitTestResults()
            }
        }
/*
        stage('Deploy') {
            steps {
                sh './gradlew publish'
            }
        }
*/
    }
}

def archiveUnitTestResults() {
    step([$class: "JUnitResultArchiver", testResults: "build/test-results/test/TEST-*.xml"])
}
