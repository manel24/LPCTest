package fr.lepotcommun.lpctest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.lepotcommun.lpctest.model.Pot;
import fr.lepotcommun.lpctest.networking.RestClient;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RestClientUnitTest {



    private  RestClient restClient;

    @Before
    public void setUp() throws Exception {
       restClient = RestClient.getSingleton();
    }

    @Test
    public void pots_success()  {
        TestSubscriber<List<Pot>> subscriber = new TestSubscriber<>();

        restClient.pots().subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();


        List<List<Pot>> onNextEvents = subscriber.getOnNextEvents();
        List<Pot> pots = onNextEvents.get(0);
        System.out.println(pots);
        Assert.assertTrue(pots != null && !pots.isEmpty());
    }



}