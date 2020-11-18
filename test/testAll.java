import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(   {testSaab95.class,
                        testVolvo240.class,
                        testScania.class,
                        testCarTransport.class,
                        testCarWorkshop.class,
                        testCarFerry.class})
public class testAll {
}
