def call () {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }
    node('workstation') {
        try {
            stage('Check Out Code') {
                sh 'ls -l'
                cleanWs()
                sh 'ls -l'
                git branch: 'main', url: 'https://github.com/sriharitirumala/cart'
                sh 'ls -l'
            }

            sh 'env'
            if (env.BRANCH_NAME != "main"){
                stage('Compile/Build'){
                    common.compile()
                }
            }


            stage('Test Cases') {
                common.testcases()

            }

            stage('Code Quality') {
                common.codequality()
            }
        } catch (e) {
            mail body: "${component} - Pipeline Failed \n ${BUILD_URL}", from: 'sritirumala30@gmail.com', subject: "${component} - Pipeline Failed", to: 'sritirumala30@gmail.com', mimeType: 'text/html'

        }
    }
}


