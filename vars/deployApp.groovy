def call (Map config) {
    script {
        def imageName = config.environment == "main" ? "d3f4ault/nodemain:${config.imageTag}" : "d3f4ault/nodedev:${config.imageTag}"
        def containerName = "node${config.environment}"
        def port = config.environment == "main" ? "3000" : "3001"
        withCredentials([usernamePassword(
                    credentialsId: 'c56850e3-4517-420b-b6e1-1f7d78eee9ba',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
        sh "docker login -u ${USER} -p ${PASS}"
        sh "docker pull ${imageName}"
        sh "docker run -d --name ${containerName} -p ${port}:3000 ${imageName}"
        }
    }
}