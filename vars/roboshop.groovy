def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP         : '172.31.10.228',

  ]
  args << params

  // End Default + Required Arguments
  pipeline{
    agent  any

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL = "${args.SLAVE_LABEL}"

    }


    stages {
      stage ('prepare  the  Artifact') {
        when {
          environment name: 'COMPONENT', value: 'frontend'
        }

        steps{
          sh '''
         zip -r ../frontend.zip *
       '''
        }
      }
      stage ('prepare  the  Artifact for login') {
        when {
          environment name: 'COMPONENT', value: 'login'
        }
        steps{
          sh '''
         zip -r ../login.zip *
       '''
        }
      }
      stage ('prepare  the  Artifact for users') {
        steps{
          sh '''
         zip -r ../users.zip *
       '''
        }
      }


      stage('upload artifact'){
        steps{
          sh '''
          curl -f -v -u admin:admin123 --upload-file ../frontend.zip http://${NEXUS_IP}:8081/repository/frontend/frontend.zip

        '''


        }

      }

    }
  }

}
















