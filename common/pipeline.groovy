def run(params) {
    def deployed = false
    try {
        stage('CheckOut') {
            checkout scm
            git branch: 'master', url: 'https://github.com/SUSE/susemanager-ci.git'
        }
        stage('Simulate compilation') {
            if (params.ForceFailure) {
                sh "exit 1"
            }
            else {
                sh "exit 0"
            }
        }
        stage('Simulate deployment') {
            sh "echo 'Product version ${params.Version}' was deployed"
            deployed = true
        }
    }
    finally {
        stage('Summary') {
            if (deployed) {
                sh "echo 'Hello World! No failures detected. Value of 'deployed' variable is ${deployed}'"
            }
            else {
                sh "echo 'There was a FAILURE! Value of 'deployed' variable is ${deployed}'"
            }
        }
    }
}

return this
