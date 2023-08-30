package eu.jpereira.trainings.designpatterns.creational.builder.reportbuilder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Customer;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.ArrayList;

public class XMLReportBuilder implements ReportBuilder {

    private Customer customer;
    private ArrayList<SoldItem> items = new ArrayList<>();
    @Override
    public Report getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("<sale><customer><name>");
        builder.append(customer.getName());
        builder.append("</name><phone>");
        builder.append(customer.getPhone());
        builder.append("</phone></customer>");

        builder.append("<items>");
        for(SoldItem item: items) {
            builder.append("<item><name>");
            builder.append(item.getName());
            builder.append("</name><quantity>");
            builder.append(item.getQuantity());
            builder.append("</quantity><price>");
            builder.append(item.getUnitPrice());
            builder.append("</price></item>");
        }
        builder.append("</items></sale>");
        return new Report(builder.toString());
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void addItem(String name, String quantity, String price) {
        items.add(new SoldItem(name, Integer.parseInt(quantity), Double.parseDouble(price)));
    }
}
