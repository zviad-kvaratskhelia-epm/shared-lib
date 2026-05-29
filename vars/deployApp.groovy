def call (Map config) {
    script {
        def imageName = config.environment == "main" ? "nodemain:${config.imageTag}" : "nodedev:${config.imageTag}"
        def containerName = "node${config.environment}"
        def port = config.environment == "main" ? "3000" : "3001"
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
        sh "docker pull ${imageName}"
        sh "docker run -d --name ${containerName} -p ${port}:3000 ${imageName}"
    }
}