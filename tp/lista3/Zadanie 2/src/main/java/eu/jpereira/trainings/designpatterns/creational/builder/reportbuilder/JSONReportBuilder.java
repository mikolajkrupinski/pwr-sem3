package eu.jpereira.trainings.designpatterns.creational.builder.reportbuilder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Customer;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.ArrayList;
import java.util.Iterator;

public class JSONReportBuilder implements ReportBuilder {

    private Customer customer;
    private ArrayList<SoldItem> items = new ArrayList<>();
    @Override
    public Report getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("sale:{customer:{");
        builder.append("name:\"");
        builder.append(customer.getName());
        builder.append("\",phone:\"");
        builder.append(customer.getPhone());
        builder.append("\"}");
        builder.append(",items:[");
        Iterator<SoldItem> it = items.iterator();
        while(it.hasNext()) {
            SoldItem item = it.next();
            builder.append("{name:\"");
            builder.append(item.getName());
            builder.append("\",quantity:");
            builder.append(item.getQuantity());
            builder.append(",price:");
            builder.append(item.getUnitPrice());
            builder.append("}");
            if(it.hasNext()) {
                builder.append(",");
            }
        }
        builder.append("]}");
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
