node {

    checkout scm

    env.DOCKER_API_VERSION="1.23"

    sh "git rev-parse --short HEAD > commit-id"

    tag = readFile('commit-id').replace("\n", "").replace("\r", "")
    tagtreat = "latest"
    appName = "promotion-storage"
    registryHost = "127.0.0.1:30400/"
    imageName = "${registryHost}${appName}:${tag}"
    imageNamev1 = "${registryHost}${appName}:${tagtreat}"
    env.BUILDIMG=imageNamev1

    stage "Build"

        sh "docker build -t ${imageName} ./promotion-storage"

    stage "Change Tag"

        sh "docker tag ${imageName} ${imageNamev1}"

    stage "Push"

        sh "docker push ${imageNamev1}"

    stage "Deploy"

        sh "sed 's#__IMAGE__#'$BUILDIMG'#' promotion-storage/deployment.yaml | kubectl apply -f -"
}
