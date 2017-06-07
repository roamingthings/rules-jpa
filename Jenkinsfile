pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                sh "./gradlew clean build"
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
    step([$class: "JUnitResultArchiver", testResults: "build/**/TEST-*.xml"])
}
