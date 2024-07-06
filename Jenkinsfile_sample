pipeline{
    
    agent any
    
    stages{
        stage("Build"){
            steps{
                echo("build the project")
            }
        }
         stage("Deploy to dev"){
            steps{
                echo("deploying to dev env")
            }
        }
        stage("Deploy to qa"){
            steps{
                echo("deploying to qa env")
            }
        }
        stage("run regression testcases"){
            steps{
                echo("run regression testcases")
            }
        }
        stage("Deploy to stage"){
            steps{
                echo("deploying stage qa env")
            }
        }
        stage("run sanity testcases"){
            steps{
                echo("run sanity testcases")
            }
        }
        stage("Deploy to prod"){
            steps{
                echo("deploying prod  env")
            }
        }
    }
}