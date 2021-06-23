def call() {
  pipeline{
    agent  any

    stages {
      stage ('prepare  the  Artifact') {
        steps{
          sh '''
         zip -r ../frontend.zip *
       '''
        }
      }
      stage('upload artifact'){
        steps{
          sh '''
          curl -f -v -u admin:admin123 --upload-file ../frontend.zip http://172.31.10.228:8081/repository/frontend/frontend.zip

        '''


        }

      }

    }
  }

}
















