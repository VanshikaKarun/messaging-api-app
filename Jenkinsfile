pipeline {
    agent any

    tools {
        // Use Maven configured in Jenkins (defined in Jenkins global tool config)
        maven "OFSSMaven"
    }

    environment {
        PROJECT_NAME = "messaging-api-app"
        NEW_JAR_NAME = "messaging-api-app-${BUILD_NUMBER}.jar"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "🔄 Checking out code from GitHub..."
                git branch: 'main', url: 'https://github.com/VanshikaKarun/messaging-api-app.git'
            }
        }

        stage('Build') {
            steps {
                echo "⚡ Building Maven Project..."
                // Use Maven with test failure ignored
                bat 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Rename JAR') {
            steps {
                script {
                    echo "📦 Renaming JAR file..."

                    // Find the original JAR file using batch script
                    def originalJar = bat(
                        script: '''
                            @echo off
                            for %%F in (target\\*.jar) do (
                                echo %%~nxF
                                goto :done
                            )
                            :done
                        ''',
                        returnStdout: true
                    ).trim()

                    echo "Found original JAR: ${originalJar}"

                    // Rename the JAR file
                    bat "rename target\\${originalJar} ${NEW_JAR_NAME}"

                    echo "✅ JAR renamed to: ${NEW_JAR_NAME}"
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                echo "📤 Archiving JAR..."
                archiveArtifacts artifacts: "target/${NEW_JAR_NAME}", fingerprint: true
            }
        }

        stage('Copy JAR to Local Path') {
            steps {
                echo "📂 Copying JAR to C:\\Vanshika\\CI_CD..."
                bat "copy /Y target\\${NEW_JAR_NAME} C:\\Vanshika\\CI_CD\\"
                echo "✅ JAR copied to C:\\Vanshika\\CI_CD"
            }
        }
    }

    post {
        success {
            echo "✅ Build Successful!"
        }
        failure {
            echo "❌ Build Failed! Please check the logs."
        }
    }
}
