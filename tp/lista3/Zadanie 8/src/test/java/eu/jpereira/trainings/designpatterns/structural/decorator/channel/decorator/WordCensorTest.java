package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WordCensorTest extends AbstractSocialChanneldDecoratorTest {

    @Test
    public void censorTheWords() {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Windows");
        arrayList.add("Linux");
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
        SocialChannel channel = builder.with(new WordCensor(arrayList)).getDecoratedChannel(props);

        // Now call channel.deliverMessage
        channel.deliverMessage("Microsoft Windows is better than Linux");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("Microsoft ### is better than ###", spyChannel.lastMessagePublished());
        System.out.println(spyChannel.lastMessagePublished());
    }
}
