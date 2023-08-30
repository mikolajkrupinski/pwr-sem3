package eu.jpereira.trainings.designpatterns.creational.builder.reportbuilder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Customer;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;

public interface ReportBuilder {
    Report getReport();
    void setCustomer(Customer customer);
    void addItem(String name, String quantity, String price);
}
