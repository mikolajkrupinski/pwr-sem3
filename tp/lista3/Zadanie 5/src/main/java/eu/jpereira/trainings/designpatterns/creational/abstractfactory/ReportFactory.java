package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public interface ReportFactory {

    public ReportBody createReportBody();

    public ReportFooter createReportFooter();

    public ReportHeader createReportHeader();
}
