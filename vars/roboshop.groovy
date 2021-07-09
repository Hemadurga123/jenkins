def call(Map params = [:]) {
  // Start Default Arguments
  def args = [
          NEXUS_IP : '172.31.10.228',
          SLAVE_LABEL : 'JAVA',

  ]
  args << params

  // End Default + Required Arguments
  pipeline {
    agent {
         label "${args.SLAVE_LABEL}"
    }

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL = "${args.SLAVE_LABEL}"
      APP_TYPE = ""

    }


    stages {
      stage('prepare  the  Artifact ------frontend') {
        steps {
          sh '''
         zip -r ../frontend.zip *
       '''
        }
      }
      stages {
        stage('prepare  the  Artifact -----login') {
          steps {
            sh '''
          zip -r ../login.zip *
            '''
          }
        }

        stages {
          stage('prepare  the  Artifact -----login') {
            steps {
              sh '''
               zip -r ../users.zip *
               '''
            }
          }
          stages {
            stage('prepare  the  Artifact -----login') {
              steps {
                sh '''
                zip -r ../todo.zip *
              '''
              }
            }


          }
        }
      }


    }
    }
  }