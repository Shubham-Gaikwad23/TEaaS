@Library('TEaaS')
import org.coep.*

node {
	stage('Checkout SCM') {
		echo 'Checking out SCM'
	}
	stage('Build') {
		echo 'Compiling'
		echo 'Building'
	}
	stage('Run regression test') {
		echo 'Running all tests'
	}
	stage('Publish test results') {
		echo 'Publishing test results'
		//String url = 'https://gist.github.com/cbergau/65e6eac87d69161f41ba'
		//httpRequest consoleLogResponseBody: true, ignoreSslErrors: true, outputFile: 'test_results.xml', responseHandle: 'NONE', url: url, wrapAsMultipart: false
		writeFile file: 'test_results.xml', text: '''<testsuite tests="3">
				 <testcase classname="test1" name="ASuccessfulTest"/>
				 <testcase classname="test2" name="AnotherSuccessfulTest"/>
				 <testcase classname="test3" name="AFailingTest">
				 <failure type="NotEnoughMemory"> Failed due to stack overflowFailed to read test report file C:\\Users\\jenkins\\AppData\\Local\\Jenkins.jenkins\\workspace\\Main\\main_job\\test_results.xml
org.dom4j.DocumentException: Error on line 76 of document  : Attribute name "data-pjax-transient" associated with an element type "meta" must be followed by the ' = ' character.
\tat org.dom4j.io.SAXReader.read(SAXReader.java:511)
\tat org.dom4j.io.SAXReader.read(SAXReader.java:392)
\tat hudson.tasks.junit.SuiteResult.parse(SuiteResult.java:178)
\tat hudson.tasks.junit.TestResult.parse(TestResult.java:378)
\tat hudson.tasks.junit.TestResult.parsePossiblyEmpty(TestResult.java:308)
\tat hudson.tasks.junit.TestResult.parse(TestResult.java:224)
\tat hudson.tasks.junit.TestResult.parse(TestResult.java:196)
\tat hudson.tasks.junit.TestResult.<init>(TestResult.java:151)
\tat hudson.tasks.junit.JUnitParser$ParseResultCallable.invoke(JUnitParser.java:144)
\tat hudson.FilePath.act(FilePath.java:1163)
\tat hudson.FilePath.act(FilePath.java:1146)
\tat hudson.tasks.junit.JUnitParser.parseResult(JUnitParser.java:107)
\tat hudson.tasks.junit.JUnitResultArchiver.parse(JUnitResultArchiver.java:149)
\tat hudson.tasks.junit.JUnitResultArchiver.parseAndSummarize(JUnitResultArchiver.java:243)
\tat hudson.tasks.junit.pipeline.JUnitResultsStepExecution.run(JUnitResultsStepExecution.java:63)
\tat hudson.tasks.junit.pipeline.JUnitResultsStepExecution.run(JUnitResultsStepExecution.java:29)
\tat org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution.lambda$start$0(SynchronousNonBlockingStepExecution.java:47)
\tat java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
\tat java.util.concurrent.FutureTask.run(FutureTask.java:266)
\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
\tat java.lang.Thread.run(Thread.java:748)
Caused by: org.xml.sax.SAXParseException; lineNumber: 76; columnNumber: 67; Attribute name "data-pjax-transient" associated with an element type "meta" must be followed by the ' = ' character.
\tat com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:204)
\tat com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:178)
\tat com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:400)
\tat com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
\tat com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1472)
\tat com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.scanAttribute(XMLNSDocumentScannerImpl.java:414)
\tat com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.scanStartElement(XMLNSDocumentScannerImpl.java:251)
\tat com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:2786)
\tat com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:605)
\tat com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.next(XMLNSDocumentScannerImpl.java:113)
\tat com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:507)
\tat com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:867)
\tat com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:796)
\tat com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:142)
\tat com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1216)
\tat com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:644)
\tat org.dom4j.io.SAXReader.read(SAXReader.java:494) </failure>
				 </testcase>
				 </testsuite>'''
		junit '**test_results.xml'
	}
	stage('Clean up') {
		echo 'Clearing workspace'
	}
	stage('Send Email') {
		echo 'Sending test report via email'
		emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
				subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
				to: 'sdg9552273694@gmail.com'
	}
}
