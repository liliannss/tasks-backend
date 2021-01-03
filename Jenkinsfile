pipeline {
    agent any
    stages {
        stage ('Build Back-End') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
		stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
		stage ('Sonar Analysis') {
			enviroment {
				scannerHome = tool 'SONAR_SCANNER'
			}
            steps {
				withSonarQubeEnv('SONAR_LOCAL') {
					bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7af9afefc2d62ee404dca5fe3b14c43550fb572f -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**, **/src/test/**,**/model/**, **Application.java"
				}
			}
        }
		stage ('Quality Gate') {
            steps {
			sleep(5)
				timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
				}
			}
        }
		stage ('Deploy Back-End') {
            steps {
				deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'target/task', war: 'target/tasks-backend.war'
			}
        }
		stage ('API Test') {
            steps {
				dir('api-test') {
					git credentialsId: 'github_login', url: 'https://github.com/liliannss/tasks-api-test.git'
					bat 'mvn test'
				}
			}
        }
		stage ('Deploy Front-End') {
            steps {
				dir('fornt-end') {
					git credentialsId: 'github_login', url: 'https://github.com/liliannss/tasks-frontend.git'
					bat 'mvn clean package'
					deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
				}
			}
        }
		stage ('Functional Test') {
            steps {
				dir('functional-test') {
					git credentialsId: 'github_login', url: 'https://github.com/liliannss/tasks-api-funcional-test.git'
					bat 'mvn test'
				}
			}
        }
		stage ('Deploy Prod') {
            steps {
				bat 'docker-compose build'
				bat 'docker-compose up -d' #libera o terminal e o Jenkins entende que o processo foi terminado
				}
			}
        }
        stage ('Health Check') {
            sleep(5)
            steps {
        		dir('functional-test') {
        		    git credentialsId: 'github_login', url: 'https://github.com/liliannss/tasks-api-funcional-test.git'
        		    bat 'mvn verify -Dskip.surefire.tests'
				}
			}
        }
    }
}