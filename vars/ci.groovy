def call() {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }
    pipeline {
        agent any

        stages {

            stage('Compile/Build') {
                steps {
                    script {
                        common.compile()
                    }
                }
            }

            stages {

                stage('Unit Test Cases') {
                    steps {
                        script {
                            common.unittestcases()
                        }
                    }
                }
            }

            stages {

                stage('Code quality') {
                    steps {
                        script {
                            common.codequality()
                        }
                    }
                }
            }
        }
        post {
            failure {
                mail body: "<h1>${component} - Pipeline Failed \n ${Build_URL}</h1>", from: "sritirumala30@gmail.com", to: "sritirumala30@gmail.com"
            }
        }
    }
}
