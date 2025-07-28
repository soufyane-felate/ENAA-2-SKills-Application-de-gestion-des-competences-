pipeline {
    agent any

    stages {
        stage('Checkout code') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/soufyane-felate/ENAA-2-SKills-Application-de-gestion-des-competences-']]
                )
            }
        }

        stage('Build with local Maven') {
            steps {
                bat '''
                    set "PATH=C:\\Users\oucht\\Desktop\\apache-maven-3.9.10\\bin;%PATH%"
                    mvn -v
                    mvn clean install
                '''
            }
        }
    }
}
