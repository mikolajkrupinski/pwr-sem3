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
package eu.jpereira.trainings.designpatterns.creational.builder.reportbuilder;

import java.util.Iterator;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

/**
 * @author jpereira
 *
 */
public class ReportAssembler {

	private SaleEntry saleEntry;

	/**
	 * @param saleEntry
	 */
	public void setSaleEntry(SaleEntry saleEntry) {
		this.saleEntry = saleEntry;

	}

	/**
	 * @param type
	 * @return
	 */
	public Report getReport(String type) {
		ReportBuilder builder;
		if (type.equals("JSON")) {
			builder = (ReportBuilder) new JSONReportBuilder();
		} else if (type.equals("XML")) {
			builder = (ReportBuilder) new XMLReportBuilder();
		} else if (type.equals("HTML")) {
			builder = (ReportBuilder) new HTMLReportBuilder();
		} else {
			return new Report("");
		}
		builder.setCustomer(saleEntry.getCustomer());
		for(SoldItem item: saleEntry.getSoldItems()) {
			builder.addItem(item.getName(), String.valueOf(item.getQuantity()), String.valueOf(item.getUnitPrice()));
		}
		return builder.getReport();
	}

}
