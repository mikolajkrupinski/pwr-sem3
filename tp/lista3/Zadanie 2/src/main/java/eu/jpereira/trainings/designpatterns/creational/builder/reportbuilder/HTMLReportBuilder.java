package eu.jpereira.trainings.designpatterns.creational.builder.reportbuilder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Customer;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.ArrayList;
import java.util.Iterator;

public class HTMLReportBuilder implements ReportBuilder {

    private Customer customer;
    private ArrayList<SoldItem> items = new ArrayList<>();
    @Override
    public Report getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("<span class=\"customerName\">");
        builder.append(customer.getName());
        builder.append("</span><span class=\"customerPhone\">");
        builder.append(customer.getPhone());
        builder.append("</span>");

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
        builder.append("</items>");
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
