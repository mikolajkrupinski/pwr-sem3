package eu.jpereira.trainings.designpatterns.creational.factorymethod.generators;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.reports.AbstractReport;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.reports.XMLReport;

public class XMLReportGenerator extends ReportGenerator {
    @Override
    protected AbstractReport createReport() {
        return new XMLReport();
    }
}
