@Library('TEaaS')
import org.coep.*

class Stages implements Serializable {
    Jenkins jenkins

    Stages() {
        jenkins = new Jenkins()
    }

    void publishTestResults() {
        jenkins.publishTestResult()
    }
}