package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

import java.util.ArrayList;

public class WordCensor extends SocialChannelDecorator {

    private final ArrayList <String> curses;

    public WordCensor(ArrayList<String> curses) {
        this.curses = curses;
    }

    public WordCensor(ArrayList<String> curses, SocialChannel channel) {
        this.curses = curses;
        this.delegate = channel;
    }

    @Override
    public void deliverMessage(String message) {
        String censored = message;
        for (String curse: curses) {
            censored = censored.replaceAll(curse,"###");
        }
        if (delegate != null) {
            delegate.deliverMessage(censored);
        }
    }
}
