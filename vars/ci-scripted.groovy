def call () {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts=""
    }
    node ('workstation') {
        try {
            stage('Compile/Build') {
                common.compile()
            }

            stage('Test Cases') {
                common.testcases()

            }

            stage('Code Quality') {
                common.codequality()
            }
        } catch (exception) {
            post {
                failure {
                    mail body: "${component} - Pipeline Failed \n ${BUILD_URL}", from: 'sritirumala30@gmail.com', subject: "${component} - Pipeline Failed", to: 'sritirumala30@gmail.com'

                }
            }
        }
    }
}
