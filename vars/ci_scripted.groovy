def call () {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }

    if (env.TAG_NAME ==~ ".*"){
        env.GTAG = "true"
    }

    node('workstation') {
        try {
            stage('Check Out Code') {
                cleanWs()
                git branch: 'main', url: 'https://github.com/sriharitirumala/cart'
            }

            sh 'env'
            if (env.BRANCH_NAME != "main"){
                stage('Compile/Build'){
                    common.compile()
                }
            }

            if (env.TAG_NAME){
                stage('Test Cases') {
                    common.testcases()

                }

            }

            stage('Code Quality') {
                common.codequality()
            }
        } catch (e) {
            mail body: "${component} - Pipeline Failed \n ${BUILD_URL}", from: 'sritirumala30@gmail.com', subject: "${component} - Pipeline Failed", to: 'sritirumala30@gmail.com', mimeType: 'text/html'

        }
    }
}


