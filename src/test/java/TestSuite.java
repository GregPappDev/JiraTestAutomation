import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({JiraCreateIssueTest.class, JiraEditIssueTest.class})
public class TestSuite {
}
