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
    }

}