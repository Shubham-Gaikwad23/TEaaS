@Library('TEaaS')
import org.coep.*

class Stages implements Serializable {
    Jenkins jenkins
    def env
    def steps
    def buildParams
    def currentBuild


    Stages(env, steps, buildParams, currentBuild) {
        this.env = env
        this.steps = steps
        this.buildParams = buildParams
        this.currentBuild = currentBuild
        jenkins = new Jenkins()
    }

    void publishTestResults() {
        jenkins.publishTestResult()
    }

    Stages createInstance() {
        return new Stages(env, steps, params, currentBuild)
    }
}