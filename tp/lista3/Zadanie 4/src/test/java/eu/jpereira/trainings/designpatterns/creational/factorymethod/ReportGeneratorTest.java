/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package eu.jpereira.trainings.designpatterns.creational.factorymethod;

import static org.junit.Assert.*;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.generators.*;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.reports.Report;
import org.junit.Test;

import javax.swing.text.html.HTML;

public class ReportGeneratorTest extends AbstractReportingTest{



	@Test
	public void testCreateJSONReport() {
		ReportData reportData = createDummyReportData();
		JSONReportGenerator generator = new JSONReportGenerator();
		Report generatedReport = generator.generateReport(reportData, "JSON");
		assertEquals("JSON Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	
	@Test
	public void testCreateXMLReport() {
		ReportData reportData = createDummyReportData();
		XMLReportGenerator generator = new XMLReportGenerator();
		Report generatedReport = generator.generateReport(reportData, "XML");
		assertEquals("XML Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	@Test
	public void testCreateHTMLReport() {
		ReportData reportData = createDummyReportData();
		HTMLReportGenerator generator = new HTMLReportGenerator();
		Report generatedReport = generator.generateReport(reportData, "HTML");
		assertEquals("HTML Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	
	@Test
	public void testCreatePDFReport() {
		ReportData reportData = createDummyReportData();
		PDFReportGenerator generator = new PDFReportGenerator();
		Report generatedReport = generator.generateReport(reportData, "PDF");
		assertEquals("PDF Report. Name: "+reportData.getName(), generatedReport.getReportContent());
	}
	

	
	
}
